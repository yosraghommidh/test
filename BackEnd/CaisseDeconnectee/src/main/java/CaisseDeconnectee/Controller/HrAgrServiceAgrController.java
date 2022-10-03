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

import CaisseDeconnectee.Entities.HrAgrServiceAgr;
import CaisseDeconnectee.Repository.HrAgrServiceAgrRepository;
import CaisseDeconnectee.Service.HrAgrServiceAgrService;

@RestController
@RequestMapping("/Contrat")
@CrossOrigin("http://localhost:8080")
public class HrAgrServiceAgrController {
	
	@Autowired 
	private HrAgrServiceAgrRepository AsaRepo ;
	
	@Autowired 
	private HrAgrServiceAgrService AsaServ;
	
	
	@PostMapping({"/addASA"})
    public ResponseEntity<HrAgrServiceAgr> save(@RequestBody HrAgrServiceAgr a) {
	try {
		  HrAgrServiceAgr u = AsaServ.save(a);
          return new ResponseEntity<>(u, HttpStatus.CREATED);
		} catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
    }
	
	
	 @GetMapping({"/getASAs"})
	 public ResponseEntity<List<HrAgrServiceAgr>> retirerAll( ){
		try {
		  List<HrAgrServiceAgr> A = AsaServ.retirerAll();
		  return new ResponseEntity<>(A, HttpStatus.OK);
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	 
	 
	 @GetMapping("/getASAById/{id}")
	 public ResponseEntity<HrAgrServiceAgr> getById(@PathVariable("id") long id) {
	   Optional<HrAgrServiceAgr> a = AsaRepo.findById(id);
	   if (a.isPresent()) {
	     return new ResponseEntity<>(a.get(), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 
	 
	 @PutMapping("/updateASA/{id}")
	 public ResponseEntity<HrAgrServiceAgr> update(@PathVariable("id") long id, @RequestBody HrAgrServiceAgr a) {
	   Optional<HrAgrServiceAgr> aa = AsaRepo.findById(id);
	   if (aa.isPresent()) {
	     return new ResponseEntity<>(AsaServ.update(a, id), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 

	 @DeleteMapping("/deleteASA/{id}")
	 public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
	   try {
	     AsaRepo.deleteById(id);
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }
	 
	 
	 @DeleteMapping("/deleteASAs")
	 public ResponseEntity<HttpStatus> deleteAll() {
	   try {
	     AsaRepo.deleteAll();
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }

}
