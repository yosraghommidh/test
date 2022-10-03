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

import CaisseDeconnectee.Entities.HrGenAgent;
import CaisseDeconnectee.Repository.HrGenAgentRepository;
import CaisseDeconnectee.Service.HrGenAgentService;

@RestController
@RequestMapping("/Agent")
@CrossOrigin("http://localhost:8080")
public class HrGenAgentController {
	@Autowired 
	private HrGenAgentRepository AgRepo ;
	
	@Autowired 
	private HrGenAgentService AgServ;
	
	
	@PostMapping({"/addAg"})
    public ResponseEntity<HrGenAgent> save(@RequestBody HrGenAgent a) {
	try {
		  HrGenAgent u = AgServ.save(a);
          return new ResponseEntity<>(u, HttpStatus.CREATED);
		} catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
    }
	
	
	 @GetMapping({"/getAgs"})
	 public ResponseEntity<List<HrGenAgent>> retirerAll( ){
		try {
		  List<HrGenAgent> A = AgServ.retirerAll();
		  return new ResponseEntity<>(A, HttpStatus.OK);
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	 
	 
	 @GetMapping("/getAgById/{id}")
	 public ResponseEntity<HrGenAgent> getById(@PathVariable("id") long id) {
	   Optional<HrGenAgent> a = AgRepo.findById(id);
	   if (a.isPresent()) {
	     return new ResponseEntity<>(a.get(), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 @GetMapping("/getname/{id}")
	 public ResponseEntity<String > getname(@PathVariable("id") long id) {
	   Optional<HrGenAgent> a = AgRepo.findById(id);
	   if (a.isPresent()) {
		   String n = a.get().getAge_name();
	     return new ResponseEntity<>(n, HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 
	 @PutMapping("/updateAg/{id}")
	 public ResponseEntity<HrGenAgent> update(@PathVariable("id") long id, @RequestBody HrGenAgent a) {
	   Optional<HrGenAgent> aa = AgRepo.findById(id);
	   if (aa.isPresent()) {
	     return new ResponseEntity<>(AgServ.update(a, id), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 

	 @DeleteMapping("/deleteAg/{id}")
	 public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
	   try {
	     AgRepo.deleteById(id);
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }
	 
	 
	 @DeleteMapping("/deleteAgs")
	 public ResponseEntity<HttpStatus> deleteAll() {
	   try {
	     AgRepo.deleteAll();
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }
}
