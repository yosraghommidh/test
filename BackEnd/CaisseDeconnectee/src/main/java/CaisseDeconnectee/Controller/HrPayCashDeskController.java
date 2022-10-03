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

import CaisseDeconnectee.Entities.HrPayCashDesk;
import CaisseDeconnectee.Repository.HrPayCashDeskRepository;
import CaisseDeconnectee.Service.HrPayCashDeskService;

@RestController
@RequestMapping("/Facture")
@CrossOrigin("http://localhost:8080")
public class HrPayCashDeskController {
	
	@Autowired 
	private HrPayCashDeskRepository HPCDeskRepo ;
	
	@Autowired 
	private HrPayCashDeskService HPServ;
	
	
	@PostMapping({"/addPCDesk"})
    public ResponseEntity<HrPayCashDesk> save(@RequestBody HrPayCashDesk a) {
	try {
		
		  HrPayCashDesk u = HPServ.save(a);
          return new ResponseEntity<>(u, HttpStatus.CREATED);
		} catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
    }
	
	
	 @GetMapping({"/getPCDesks"})
	 public ResponseEntity<List<HrPayCashDesk>> retirerAll( ){
		try {
		  List<HrPayCashDesk> A = HPServ.retirerAll();
		  return new ResponseEntity<>(A, HttpStatus.OK);
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	 
	 
	 @GetMapping("/getPCDeskById/{id}")
	 public ResponseEntity<HrPayCashDesk> getById(@PathVariable("id") long id) {
	   Optional<HrPayCashDesk> a = HPCDeskRepo.findById(id);
	   if (a.isPresent()) {
	     return new ResponseEntity<>(a.get(), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 
	 
	 @PutMapping("/updatePCDesk/{id}")
	 public ResponseEntity<HrPayCashDesk> update(@PathVariable("id") long id, @RequestBody HrPayCashDesk a) {
	   Optional<HrPayCashDesk> aa = HPCDeskRepo.findById(id);
	   if (aa.isPresent()) {
	     return new ResponseEntity<>(HPServ.update(a, id), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 

	 @DeleteMapping("/deletePCDesk/{id}")
	 public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
	   try {
	     HPCDeskRepo.deleteById(id);
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }
	 
	 
	 @DeleteMapping("/deletePCDesks")
	 public ResponseEntity<HttpStatus> deleteAll() {
	   try {
	     HPCDeskRepo.deleteAll();
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }


}
