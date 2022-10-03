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

import CaisseDeconnectee.Entities.HrParty;
import CaisseDeconnectee.Repository.HrPartyRepository;
import CaisseDeconnectee.Service.HrPartyService;

@RestController
@RequestMapping("/Client")
@CrossOrigin("http://localhost:8080")
public class HrPartyController {
	
	@Autowired 
	private HrPartyRepository HPRepo ;
	
	@Autowired 
	private HrPartyService HPServ;
	
	
	@PostMapping({"/addC"})
    public ResponseEntity<HrParty> save(@RequestBody HrParty a) {
	try {
		  HrParty u = HPServ.save(a);
          return new ResponseEntity<>(u, HttpStatus.CREATED);
		} catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
    }
	
	
	 @GetMapping({"/getCs"})
	 public ResponseEntity<List<HrParty>> retirerAll( ){
		try {
		  List<HrParty> A = HPServ.retirerAll();
		  return new ResponseEntity<>(A, HttpStatus.OK);
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	 
	 
	 @GetMapping("/getCById/{id}")
	 public ResponseEntity<HrParty> getById(@PathVariable("id") long id) {
	   Optional<HrParty> a = HPRepo.findById(id);
	   if (a.isPresent()) {
	     return new ResponseEntity<>(a.get(), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 
	 
	 @PutMapping("/updateC/{id}")
	 public ResponseEntity<HrParty> update(@PathVariable("id") long id, @RequestBody HrParty a) {
	   Optional<HrParty> aa = HPRepo.findById(id);
	   if (aa.isPresent()) {
	     return new ResponseEntity<>(HPServ.update(a, id), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 

	 @DeleteMapping("/deleteC/{id}")
	 public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
	   try {
	     HPRepo.deleteById(id);
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }
	 
	 
	 @DeleteMapping("/deleteCs")
	 public ResponseEntity<HttpStatus> deleteAll() {
	   try {
	     HPRepo.deleteAll();
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }

}
