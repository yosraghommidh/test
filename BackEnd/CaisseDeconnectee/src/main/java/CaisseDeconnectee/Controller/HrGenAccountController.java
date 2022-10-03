package CaisseDeconnectee.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CaisseDeconnectee.Entities.HrGenAccount;
import CaisseDeconnectee.Repository.HrGenAccountRepository;
import CaisseDeconnectee.Service.HrGenAccountService;

@RestController
@RequestMapping("/Account")
@CrossOrigin("http://localhost:8080")
public class HrGenAccountController {
	@Autowired 
	private HrGenAccountRepository AccRepo ;
	
	@Autowired 
	private HrGenAccountService AccServ;
	
	
	@PostMapping({"/addAcc"})
    public ResponseEntity<HrGenAccount> save(@RequestBody HrGenAccount a) {
	try {
		  HrGenAccount u = AccServ.save(a);
          return new ResponseEntity<>(u, HttpStatus.CREATED);
		} catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
    }
	
	
	 @GetMapping({"/getAccs"})
	 public ResponseEntity<List<HrGenAccount>> retirerAll( ){
		try {
		  List<HrGenAccount> A = AccServ.retirerAll();
		  return new ResponseEntity<>(A, HttpStatus.OK);
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	 
	 
	 @GetMapping("/getAccById/{id}")
	 public ResponseEntity<HrGenAccount> getById(@PathVariable("id") long id) {
	   Optional<HrGenAccount> a = AccRepo.findById(id);
	   if (a.isPresent()) {
	     return new ResponseEntity<>(a.get(), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 
	 
	 @PutMapping("/updateAcc/{id}")
	 public ResponseEntity<HrGenAccount> update(@PathVariable("id") long id, @RequestBody HrGenAccount a) {
	   Optional<HrGenAccount> aa = AccRepo.findById(id);
	   if (aa.isPresent()) {
	     return new ResponseEntity<>(AccServ.update(a, id), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 

	 @DeleteMapping("/deleteAcc/{id}")
	 public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
	   try {
	     AccRepo.deleteById(id);
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }
	 
	 
	 @DeleteMapping("/deleteAccs")
	 public ResponseEntity<HttpStatus> deleteAll() {
	   try {
	     AccRepo.deleteAll();
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }


}
