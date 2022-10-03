package CaisseDeconnectee.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import CaisseDeconnectee.Entities.HrPayCashDesk;
import CaisseDeconnectee.Entities.HrPayCashDeskSession;
import CaisseDeconnectee.Repository.HrGenAgentRepository;
import CaisseDeconnectee.Repository.HrPayCashDeskRepository;
import CaisseDeconnectee.Repository.HrPayCashDeskSessionRepository;
import CaisseDeconnectee.Service.HrGenAgentService;
import CaisseDeconnectee.Service.HrPayCashDeskService;
import CaisseDeconnectee.Service.HrPayCashDeskSessionService;

@RestController
@RequestMapping("/Facture")
@CrossOrigin("http://localhost:8080")
public class HrPayCashDeskSessionController {
	
	@Autowired 
	private HrGenAgentRepository AgRepo ;
	
	@Autowired 
	private HrGenAgentService AgServ ;
	
	@Autowired 
	 private HrPayCashDeskRepository caisseRepo ; 
	
	@Autowired 
	private HrPayCashDeskSessionRepository HPCDeskSRepo ;
	
	@Autowired 
	private HrPayCashDeskSessionService HPServ;
	
	@Autowired 
	private HrPayCashDeskService CaisseServ;
	
	
	
	@PostMapping({"/addPCDesSessionk"})
    public ResponseEntity<HrPayCashDeskSession> save(@RequestBody HrPayCashDeskSession a) {
	try {
			HrPayCashDesk c = a.getCAH_ID();
			CaisseServ.save(c);
		
		  HrPayCashDeskSession u = HPServ.save(a);
          return new ResponseEntity<>(u, HttpStatus.CREATED);
		} catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
    }
	@PostMapping({"/addPCDS/{ida}/{mnt}/{start}/{end}"})
    public ResponseEntity<HrPayCashDeskSession> savesession(
    		@PathVariable("ida")   long ida ,@PathVariable("mnt")   long mnt , @PathVariable("start")   String s ,
    		@PathVariable("end")   String e ,@RequestBody   HrPayCashDesk idc  ) {
	try {
		HrPayCashDeskSession c = new HrPayCashDeskSession();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		LocalDateTime st = LocalDateTime.parse(s, formatter);
		LocalDateTime en = LocalDateTime.parse(e, formatter);

		c.setCss_startdt(st);
		c.setCss_enddt(en);
		c.setMnt_initial(mnt);
		//Optional<HrPayCashDesk> cais = caisseRepo.findById(idc);
		//HrPayCashDesk d =CaisseServ.save(cais.get());
		c.setCAH_ID(idc);
		//Optional<HrGenAgent> ag = AgRepo.findById(ida);
		//HrGenAgent aa = AgServ.save(ag.get());
		c.setAGEID(ida);
		
		  HrPayCashDeskSession u = HPServ.save(c);
          return new ResponseEntity<>(u, HttpStatus.CREATED);
		} catch (Exception e1) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
    }
	
	
	 @GetMapping({"/getPCDeskSessions"})
	 public ResponseEntity<List<HrPayCashDeskSession>> retirerAll( ){
		try {
		  List<HrPayCashDeskSession> A = HPServ.retirerAll();
		  return new ResponseEntity<>(A, HttpStatus.OK);
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	 
	 
	 @GetMapping("/getPCDeskSessionById/{id}")
	 public ResponseEntity<HrPayCashDeskSession> getById(@PathVariable("id") long id) {
	   Optional<HrPayCashDeskSession> a = HPCDeskSRepo.findById(id);
	   if (a.isPresent()) {
	     return new ResponseEntity<>(a.get(), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 
	 
	 @PutMapping("/updatePCDeskSession/{id}")
	 public ResponseEntity<HrPayCashDeskSession> update(@PathVariable("id") long id, @RequestBody HrPayCashDeskSession a) {
	   Optional<HrPayCashDeskSession> aa = HPCDeskSRepo.findById(id);
	   if (aa.isPresent()) {
	     return new ResponseEntity<>(HPServ.update(a, id), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 

	 @DeleteMapping("/deletePCDeskSession/{id}")
	 public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
	   try {
	     HPCDeskSRepo.deleteById(id);
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }
	 
	 
	 @DeleteMapping("/deletePCDeskSessions")
	 public ResponseEntity<HttpStatus> deleteAll() {
	   try {
	     HPCDeskSRepo.deleteAll();
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }
	 @GetMapping("/getCaisseAvailIdAg/{id}")
	 public ResponseEntity<HrPayCashDeskSession> getCaisseById(@PathVariable("id") long id) {
	   HrPayCashDeskSession a = HPCDeskSRepo.findByAGEID(id);
	   LocalDateTime now = LocalDateTime.now();
	   
	   if (now.isAfter(a.getCss_startdt()) && now.isBefore(a.getCss_enddt())) {
		   HrPayCashDesk Caisse = a.getCAH_ID();
	     return new ResponseEntity<>(a, HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 
	 @GetMapping("/getavilAgent")
	 public ResponseEntity<List<HrGenAgent>> getAvailAgent() {
	   List<HrPayCashDeskSession> aa = HPCDeskSRepo.findAll();
	   LocalDateTime now = LocalDateTime.now();
	   List<HrGenAgent> lag = new ArrayList<HrGenAgent>();
	   lag=AgRepo.findAll();
	   List<HrGenAgent> la = new ArrayList<HrGenAgent>();

	   for (HrPayCashDeskSession a : aa ) {
	   if (now.isAfter(a.getCss_startdt()) && now.isBefore(a.getCss_enddt())) {
		   long id = a.getAGEID();
		   Optional<HrGenAgent> ag = AgRepo.findById(id);
		   
		   la.add(ag.get());
	   
	   } 
	   boolean lagent = lag.removeAll(la) ;
	 }
	     return new ResponseEntity<>(lag, HttpStatus.OK);

	   }
	 
	 @GetMapping("/getavilCaisse")
	 public ResponseEntity<List<HrPayCashDesk>> getAvailCaisse() {
	   List<HrPayCashDeskSession> aa = HPCDeskSRepo.findAll();
	   LocalDateTime now = LocalDateTime.now();
	   List<HrPayCashDesk> lag = new ArrayList<HrPayCashDesk>();
	   lag=caisseRepo.findAll();
	   List<HrPayCashDesk> la = new ArrayList<HrPayCashDesk>();

	   for (HrPayCashDeskSession a : aa ) {
	   if (now.isAfter(a.getCss_startdt()) && now.isBefore(a.getCss_enddt())) {
		   long id = a.getAGEID();
		   
		   la.add(a.getCAH_ID());
	   
	   } 
	   lag.removeAll(la) ;
	 }
	     return new ResponseEntity<>(lag, HttpStatus.OK);

	   }
	 
	 @GetMapping("/getsessions")
	 public ResponseEntity<List<HrPayCashDeskSession>> getsession() {
	   List<HrPayCashDeskSession> aa = HPCDeskSRepo.findAll();
	   LocalDateTime now = LocalDateTime.now();
	   List<HrPayCashDeskSession> sess = new ArrayList<HrPayCashDeskSession>();
	   for (HrPayCashDeskSession a : aa ) {
	   if (now.isAfter(a.getCss_startdt()) && now.isBefore(a.getCss_enddt())) {
		   
		   
		   sess.add(a);
	   
	   } 
	 }
	     return new ResponseEntity<>(sess, HttpStatus.OK);

	   }
	 


}
