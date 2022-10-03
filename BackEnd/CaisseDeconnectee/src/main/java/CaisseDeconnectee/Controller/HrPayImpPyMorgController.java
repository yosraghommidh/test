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

import CaisseDeconnectee.Entities.HrPayImpPyMorg;
import CaisseDeconnectee.Repository.HrPayImpPyMorgRepository;
import CaisseDeconnectee.Service.HrPayImpPyMorgService;

@RestController
@RequestMapping("/Facture")
@CrossOrigin("http://localhost:8080")
public class HrPayImpPyMorgController {
	
	@Autowired 
	private HrPayImpPyMorgRepository HPIPMRepo ;
	
	@Autowired 
	private HrPayImpPyMorgService HPIPMServ;
	
	
	@PostMapping({"/addPIPM"})
    public ResponseEntity<HrPayImpPyMorg> save(@RequestBody HrPayImpPyMorg a) {
	try {
		  HrPayImpPyMorg u = HPIPMServ.save(a);
          return new ResponseEntity<>(u, HttpStatus.CREATED);
		} catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
    }
	
	
	 @GetMapping({"/getPIPM"})
	 public ResponseEntity<List<HrPayImpPyMorg>> retirerAll( ){
		try {
		  List<HrPayImpPyMorg> A = HPIPMServ.retirerAll();
		  return new ResponseEntity<>(A, HttpStatus.OK);
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	 
	 
	 @GetMapping("/getPIPMById/{id}")
	 public ResponseEntity<HrPayImpPyMorg> getById(@PathVariable("id") long id) {
	   Optional<HrPayImpPyMorg> a = HPIPMRepo.findById(id);
	   if (a.isPresent()) {
	     return new ResponseEntity<>(a.get(), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 
	 
	 @PutMapping("/updatePIPM/{id}")
	 public ResponseEntity<HrPayImpPyMorg> update(@PathVariable("id") long id, @RequestBody HrPayImpPyMorg a) {
	   Optional<HrPayImpPyMorg> aa = HPIPMRepo.findById(id);
	   if (aa.isPresent()) {
	     return new ResponseEntity<>(HPIPMServ.update(a, id), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 

	 @DeleteMapping("/deletePIPM/{id}")
	 public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
	   try {
	     HPIPMRepo.deleteById(id);
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }
	 
	 
	 @DeleteMapping("/deletePIPMs")
	 public ResponseEntity<HttpStatus> deleteAll() {
	   try {
	     HPIPMRepo.deleteAll();
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }

}
