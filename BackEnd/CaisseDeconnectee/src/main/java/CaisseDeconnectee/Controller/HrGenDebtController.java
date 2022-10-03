package CaisseDeconnectee.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

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

import com.lowagie.text.DocumentException;

import CaisseDeconnectee.Entities.HrGenAccount;
import CaisseDeconnectee.Entities.HrGenDebt;
import CaisseDeconnectee.Entities.HrParty;
import CaisseDeconnectee.Entities.PDFExporter;
import CaisseDeconnectee.Repository.HrGenDebtRepository;
import CaisseDeconnectee.Service.HrGenAccountService;
import CaisseDeconnectee.Service.HrGenDebtService;

@RestController
@RequestMapping("/Debt")
@CrossOrigin("http://localhost:8080")
public class HrGenDebtController {
	
	@Autowired 
	private HrGenDebtRepository HGDRepo ;
	
	@Autowired 
	private HrGenDebtService HGDServ;
	
	 @Autowired
	 private HrGenAccountService accServ ; 
	
	@PostMapping({"/addDebt"})
    public ResponseEntity<HrGenDebt> save(@RequestBody HrGenDebt a) {
	try {
			LocalDate todaysDate = LocalDate.now();
			Date d =Date.from(todaysDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			a.setDeb_date(d);
			HrGenDebt u = HGDServ.save(a);
          return new ResponseEntity<>(u, HttpStatus.CREATED);
		} catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
    }
	
	
	 @GetMapping({"/getDebts"})
	 public ResponseEntity<List<HrGenDebt>> retirerAll( ){
		try {
		  List<HrGenDebt> A = HGDServ.retirerAll();
		  return new ResponseEntity<>(A, HttpStatus.OK);
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	 
	 
	 @GetMapping("/getDebtById/{id}")
	 public ResponseEntity<HrGenDebt> getById(@PathVariable("id") long id) {
	   Optional<HrGenDebt> a = HGDRepo.findById(id);
	   if (a.isPresent()) {
	     return new ResponseEntity<>(a.get(), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(null,HttpStatus.OK);
	   }
	 }
	 @GetMapping("/getDebtByRef/{ref}")
	 public ResponseEntity<List<HrGenDebt>> getByRef(@PathVariable("ref") String ref) {
	   List<HrGenDebt> a = HGDRepo.findBydebrefeStartsWith(ref);
	   if (a.isEmpty()==false) {
	     return new ResponseEntity<>(a, HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(null,HttpStatus.OK);
	   }
	 }
	 
	 @GetMapping("/getDebtByClientId/{id}")
	 public ResponseEntity<List<HrGenDebt>> getByCId(@PathVariable("id") long id) {
		 List<HrGenDebt> a = HGDRepo.findbyCid(id);
		 System.out.printf("here",a);
		  return new ResponseEntity<>(a, HttpStatus.OK);
	 }
	 @GetMapping("/getDebtByClientRef/{ref}")
	 public ResponseEntity<List<HrGenDebt>> getByCref(@PathVariable("ref") String ref) {
		 List<HrGenDebt> a = HGDRepo.findbyClientref(ref);
		 System.out.printf("here",a);
		  return new ResponseEntity<>(a, HttpStatus.OK);
	 }
	 
	 @GetMapping("/getDebtByCompteId/{id}")
	 public ResponseEntity<List<HrGenDebt>> getByCompteId(@PathVariable("id") long id) {
		 List<HrGenDebt> a = HGDRepo.findbyCompteid(id);
		 System.out.printf("here",a);
		  return new ResponseEntity<>(a, HttpStatus.OK);
	 }
	 
	 @GetMapping("/getDebtByComptetRef/{ref}")
	 public ResponseEntity<List<HrGenDebt>> getByCompteref(@PathVariable("ref") String ref) {
		 List<HrGenDebt> a = HGDRepo.findbyCompteref(ref);
		  return new ResponseEntity<>(a, HttpStatus.OK);
	 }
	 
	 
	 @PutMapping("/updateDebt/{id}")
	 public ResponseEntity<HrGenDebt> update(@PathVariable("id") long id, @RequestBody HrGenDebt a) {
	   Optional<HrGenDebt> aa = HGDRepo.findById(id);
	   if (aa.isPresent()) {
	     return new ResponseEntity<>(HGDServ.update(a, id), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 

	 @DeleteMapping("/deleteDebt/{id}")
	 public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
	   try {
	     HGDRepo.deleteById(id);
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }
	 
	 
	 @DeleteMapping("/deleteDebts")
	 public ResponseEntity<HttpStatus> deleteAll() {
	   try {
	     HGDRepo.deleteAll();
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }
	 
	 @GetMapping("/export/pdf/{id}")
	    public void exportToPDF(HttpServletResponse response ,@PathVariable("id")  long id ) throws DocumentException, IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=facture_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	         
	        Optional<HrGenDebt> fact	 = HGDRepo.findById(id);
	        HrGenDebt facture = fact.get();
	        System.out.println(facture.getAdresse()); 
	        PDFExporter exporter = new PDFExporter(facture);
	        exporter.export(response);
	         
	    }
	 @GetMapping({"/getClientId/{id}"})
	 public ResponseEntity<HrParty> getClientId( @PathVariable("id")  long id){
		try {
		  Optional<HrGenDebt> A = HGDRepo.findById(id);
		  HrGenDebt HG =A.get();
		  HrParty HP = HG.getPAR_ID();
		  return new ResponseEntity<>(HP, HttpStatus.OK);
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	 
	 @GetMapping({"/getAccountsId/{id}"})
	 public ResponseEntity<List<HrGenAccount>> getCompteId( @PathVariable("id")  long id){
		try {
		  Optional<HrGenDebt> A = HGDRepo.findById(id);
		  HrGenDebt HG =A.get();
		  HrParty HP = HG.getPAR_ID();
		  HrGenAccount HA = HG.getACO_ID();
		  List<HrGenAccount> C = new ArrayList<HrGenAccount>();
		  C.add(HA);
		  List<HrGenAccount> ClientCs= HP.getPar_account() ;
		  for (HrGenAccount ACC :ClientCs )
		  {
			  if (ACC.getAco_id()!=HA.getAco_id())
				  C.add(ACC);
		  }
		  return new ResponseEntity<>(C, HttpStatus.OK);
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	 
	 @GetMapping({"/getAccbyId/{id}/{idF}"})
	 public ResponseEntity<HrGenAccount> getAcc(@PathVariable("id")   String id , @PathVariable("idF")long idFact){
		try {
		  Optional<HrGenDebt> A = HGDRepo.findById(idFact);
		  HrGenDebt AA =A.get();
		  
		  List<HrGenAccount> C = AA.getPAR_ID().getPar_account();
		  for (HrGenAccount c : C)
		  { String aref = c.getAco_ref();
		  if ( aref.equalsIgnoreCase(id)) {
				  return new ResponseEntity<>(c, HttpStatus.OK);
			  }
			  else continue ;
	
		  }
		
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
		return null;
	}

	 @GetMapping({"/getFactByAccId/{id}"})
	 public ResponseEntity< HrGenDebt> getAcc( @PathVariable("id")long id){
		try {
		  List<HrGenDebt> A = HGDRepo.findAll();
		  List<HrGenDebt> B = new  ArrayList<HrGenDebt>(); 
		 for (HrGenDebt a : A)
		 {
				if ( a.getACO_ID().getAco_id()==id) {
					  B.add(a);
						 return new ResponseEntity<>(a, HttpStatus.OK);

				  }
				  else continue ;
		
			  }
		 }	  
		 catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
		return null;
		
	}
	 
	 @PutMapping("/PayerFact/{id}/{montant}")
	 public ResponseEntity<HrGenDebt> PayerFact(@PathVariable("id") long id,
			 @PathVariable("montant") int m
			 ) {
	   Optional<HrGenDebt> aa = HGDRepo.findById(id);
	   if (aa.isPresent()) {
		   HrGenDebt a =  aa.get();
		   a.setDEB_AMOUNT_CASH(a.getDEB_AMOUNT_CASH()+m);
		   System.out.print(a.getDEB_AMOUNT_CASH()+m);
	     return new ResponseEntity<>(HGDServ.update(a, id), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 
	 
}


