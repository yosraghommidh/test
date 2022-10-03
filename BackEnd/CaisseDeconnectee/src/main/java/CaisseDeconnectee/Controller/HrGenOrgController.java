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

import CaisseDeconnectee.Entities.HrGenOrg;
import CaisseDeconnectee.Repository.HrGenOrgRepository;
import CaisseDeconnectee.Service.HrGenOrgService;

@RestController
@RequestMapping("/Org")
@CrossOrigin("http://localhost:8080")
public class HrGenOrgController {
	

	@Autowired 
	private HrGenOrgRepository HGORepo ;
	
	@Autowired 
	private HrGenOrgService HGOServ;
	
	
	@PostMapping({"/addOrg"})
    public ResponseEntity<HrGenOrg> save(@RequestBody HrGenOrg a) {
	try {
		  HrGenOrg u = HGOServ.save(a);
          return new ResponseEntity<>(u, HttpStatus.CREATED);
		} catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
    }
	
	
	 @GetMapping({"/getOrgs"})
	 public ResponseEntity<List<HrGenOrg>> retirerAll( ){
		try {
		  List<HrGenOrg> A = HGOServ.retirerAll();
		  return new ResponseEntity<>(A, HttpStatus.OK);
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	 
	 
	 @GetMapping("/getOrgById/{id}")
	 public ResponseEntity<HrGenOrg> getById(@PathVariable("id") long id) {
	   Optional<HrGenOrg> a = HGORepo.findById(id);
	   if (a.isPresent()) {
	     return new ResponseEntity<>(a.get(), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 
	 
	 @PutMapping("/updateOrg/{id}")
	 public ResponseEntity<HrGenOrg> update(@PathVariable("id") long id, @RequestBody HrGenOrg a) {
	   Optional<HrGenOrg> aa = HGORepo.findById(id);
	   if (aa.isPresent()) {
	     return new ResponseEntity<>(HGOServ.update(a, id), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 

	 @DeleteMapping("/deleteOrg/{id}")
	 public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
	   try {
	     HGORepo.deleteById(id);
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }
	 
	 
	 @DeleteMapping("/deleteOrgs")
	 public ResponseEntity<HttpStatus> deleteAll() {
	   try {
	     HGORepo.deleteAll();
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }

}
