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

import CaisseDeconnectee.Entities.HrPayCashDebt;
import CaisseDeconnectee.Repository.HrPayCashDebtRepository;
import CaisseDeconnectee.Service.HrPayCashDebtService;

@RestController
@RequestMapping("/Facture")
@CrossOrigin("http://localhost:8080")
public class HrPayCashDebtController {

	@Autowired 
	private HrPayCashDebtRepository HPCDRepo ;
	
	@Autowired 
	private HrPayCashDebtService HPServ;
	
	
	@PostMapping({"/addPCD"})
    public ResponseEntity<HrPayCashDebt> save(@RequestBody HrPayCashDebt a) {
	try {
		  HrPayCashDebt u = HPServ.save(a);
          return new ResponseEntity<>(u, HttpStatus.CREATED);
		} catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
    }
	
	
	 @GetMapping({"/getPCDs"})
	 public ResponseEntity<List<HrPayCashDebt>> retirerAll( ){
		try {
		  List<HrPayCashDebt> A = HPServ.retirerAll();
		  return new ResponseEntity<>(A, HttpStatus.OK);
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	 
	 
	 @GetMapping("/getPCDById/{id}")
	 public ResponseEntity<HrPayCashDebt> getById(@PathVariable("id") long id) {
	   Optional<HrPayCashDebt> a = HPCDRepo.findById(id);
	   if (a.isPresent()) {
	     return new ResponseEntity<>(a.get(), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 
	 
	 @PutMapping("/updatePCD/{id}")
	 public ResponseEntity<HrPayCashDebt> update(@PathVariable("id") long id, @RequestBody HrPayCashDebt a) {
	   Optional<HrPayCashDebt> aa = HPCDRepo.findById(id);
	   if (aa.isPresent()) {
	     return new ResponseEntity<>(HPServ.update(a, id), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 

	 @DeleteMapping("/deletePCD/{id}")
	 public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
	   try {
	     HPCDRepo.deleteById(id);
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }
	 
	 
	 @DeleteMapping("/deletePCDs")
	 public ResponseEntity<HttpStatus> deleteAll() {
	   try {
	     HPCDRepo.deleteAll();
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }

}
