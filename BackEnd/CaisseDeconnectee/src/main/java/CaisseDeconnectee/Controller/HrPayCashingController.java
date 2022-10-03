package CaisseDeconnectee.Controller;

import java.time.LocalDateTime;
import java.util.Date;
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
import CaisseDeconnectee.Entities.HrParty;
import CaisseDeconnectee.Entities.HrPayCashDebt;
import CaisseDeconnectee.Entities.HrPayCashDeskMove;
import CaisseDeconnectee.Entities.HrPayCashDeskSession;
import CaisseDeconnectee.Entities.HrPayCashing;
import CaisseDeconnectee.Entities.HrPayImpPyMorg;
import CaisseDeconnectee.Repository.HrGenAgentRepository;
import CaisseDeconnectee.Repository.HrPayCashDeskSessionRepository;
import CaisseDeconnectee.Repository.HrPayCashingRepository;
import CaisseDeconnectee.Service.HrPayCashDebtService;
import CaisseDeconnectee.Service.HrPayCashDeskMoveService;
import CaisseDeconnectee.Service.HrPayCashingService;
import CaisseDeconnectee.Service.HrPayImpPyMorgService;

@RestController
@RequestMapping("/Facture")
@CrossOrigin("http://localhost:8080")
public class HrPayCashingController {
	
	@Autowired 
	private HrPayCashingRepository HPCashRepo ;
	
	@Autowired 
	private HrPayCashDeskSessionRepository HrPayCashDeskSessionRepository ;
	
	@Autowired 
	private HrGenAgentRepository HrGenAgentRepository ;
	
	@Autowired 
	private HrPayCashingService HPServ;
	
	@Autowired 
	private HrPayCashDeskMoveService PCDMServ ;
	
	@Autowired
	private HrPayImpPyMorgService HPIPMServ ;
	
	@Autowired 
	private HrPayCashDebtService pcdServ ;
	
	@PostMapping({"/addPCash"})
    public ResponseEntity<HrPayCashing> save(@RequestBody HrPayCashing a) {
	try {
		  HrPayCashing u = HPServ.save(a);
          return new ResponseEntity<>(u, HttpStatus.CREATED);
		} catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
    }
	
	
	 @GetMapping({"/getPCash"})
	 public ResponseEntity<List<HrPayCashing>> retirerAll( ){
		try {
		  List<HrPayCashing> A = HPServ.retirerAll();
		  return new ResponseEntity<>(A, HttpStatus.OK);
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	 
	 
	 @GetMapping("/getPCashById/{id}")
	 public ResponseEntity<HrPayCashing> getById(@PathVariable("id") long id) {
	   Optional<HrPayCashing> a = HPCashRepo.findById(id);
	   if (a.isPresent()) {
	     return new ResponseEntity<>(a.get(), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 
	 
	 @PutMapping("/updatePCash/{id}")
	 public ResponseEntity<HrPayCashing> update(@PathVariable("id") long id, @RequestBody HrPayCashing a) {
	   Optional<HrPayCashing> aa = HPCashRepo.findById(id);
	   if (aa.isPresent()) {
	     return new ResponseEntity<>(HPServ.update(a, id), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 

	 @DeleteMapping("/deletePCash/{id}")
	 public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
	   try {
	     HPCashRepo.deleteById(id);
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }
	 
	 
	 @DeleteMapping("/deletePCashs")
	 public ResponseEntity<HttpStatus> deleteAll() {
	   try {
	     HPCashRepo.deleteAll();
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }
		@PostMapping({"/PayFact/{montant}/{agent}/{moder}/{sessionc}/{idF}"})
	    public ResponseEntity<HrPayCashing> savePayFact(
	    		 @PathVariable("cs") long cs,
	    		  @PathVariable("m") int m , @PathVariable("mr") String MR
	    		,@PathVariable("a")  long agent, @PathVariable("idF") long idf  ,@RequestBody  HrParty pay) {
		try {
			  Optional<HrGenAgent> ag = HrGenAgentRepository.findById(agent); 
			  HrGenAgent age = ag.get();
			  HrPayCashing a = new HrPayCashing();
			  a.setCsh_date(LocalDateTime.now());
			  System.out.println(a.getCsh_date());
			  HrPayCashDeskMove MvCais = new HrPayCashDeskMove();
			  MvCais.setCam_refe("Encaiss"+a.getCsh_date().toString());
			  Optional<HrPayCashDeskSession> cds = HrPayCashDeskSessionRepository.findById(cs);
			  HrPayCashDeskSession CS = cds.get();
			  MvCais.setCSS_ID(CS);
			  MvCais.setCam_date(a.getCsh_date());
			  MvCais.setCam_amount(m);
			  HrPayCashDeskMove mc=PCDMServ.save(MvCais);
			  System.out.println( mc.getCam_date());
			  System.out.printf(mc.getCam_refe());
			  a.setCAM_ID(mc);
			  HrPayImpPyMorg mreg = new HrPayImpPyMorg();
			  mreg.setCAH_ID(CS.getCAH_ID().getCah_id());
			  mreg.setPym_name(MR);
			  HrPayImpPyMorg mregg =HPIPMServ.save(mreg);
			  a.setPYM_ID(mregg);
			  a.setAGE_ID(age);
			  a.setPAR_ID(pay);
			  a.setCsh_amount(m);
			  HrPayCashing u = HPServ.save(a);
			  HrPayCashDebt pd = new HrPayCashDebt();
			  pd.setCSH_ID(u.getCsh_id());
			  pd.setDEB_ID(idf);
			  pd.setPcd_date(u.getCsh_date());
			  pd.setPcd_amount(u.getCsh_amount());
			  pcdServ.save(pd);
	          return new ResponseEntity<>(u, HttpStatus.CREATED);
			} catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	    }


}
