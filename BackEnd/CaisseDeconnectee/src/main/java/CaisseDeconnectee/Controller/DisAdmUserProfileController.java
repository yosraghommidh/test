package CaisseDeconnectee.Controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CaisseDeconnectee.Entities.DisAdmProfile;
import CaisseDeconnectee.Entities.DisAdmUser;
import CaisseDeconnectee.Entities.DisAdmUserProfile;
import CaisseDeconnectee.Entities.HrGenAgent;
import CaisseDeconnectee.Repository.DisAdmUserProfileRepository;
import CaisseDeconnectee.Service.DisAdmProfileService;
import CaisseDeconnectee.Service.DisAdmUserProfileService;
import CaisseDeconnectee.Service.DisAdmUserService;
import CaisseDeconnectee.Service.HrGenAgentService;
import CaisseDeconnectee.Service.SequenceGeneratorService;

@RestController
@RequestMapping("/UserProfile")
@CrossOrigin("http://localhost:8080")

public class DisAdmUserProfileController {
	@Autowired 
	private DisAdmUserProfileRepository AdmUPRepo ;
	
	@Autowired 
	private DisAdmUserProfileService AdmUPServ;
	
	@Autowired 
	private DisAdmUserService AdmUServ;
	
	@Autowired 
	private DisAdmProfileService AdmPServ;
	
	@Autowired
	private HrGenAgentService AgentServ ;
	
	@Autowired 
	SequenceGeneratorService SequenceGeneratorService ;
	
	@PostMapping({"/addUserProfil"})
    public ResponseEntity<DisAdmUserProfile> saveUserProfile(@RequestBody DisAdmUserProfile AdmPU) {
	try {
		  DisAdmUser u =AdmPU.getDIS_ADMUSER();
		  DisAdmUser usr = AdmUServ.saveUser(u);
		  AdmPU.setDIS_ADMUSER(usr);
		  List<DisAdmProfile> ps =AdmPU.getDIS_ADMPROFILE();
		  List<DisAdmProfile> pps = new ArrayList<DisAdmProfile>() ;
		  for (DisAdmProfile p : ps)
			  pps.add( AdmPServ.saveProfile(p));
		  AdmPU.setDIS_ADMPROFILE(pps);
		  AdmPU.setDIS_USE_ID(SequenceGeneratorService.generateSequence(DisAdmUserProfile.SEQUENCE_NAME));
		  System.out.println(AdmPU);
		  DisAdmUserProfile up = AdmUPServ.saveUserProfile(AdmPU);
			HrGenAgent Ag = new HrGenAgent();
			Ag.setAge_id(SequenceGeneratorService.generateSequence(HrGenAgent.SEQUENCE_NAME));

			Ag.setAge_login(AdmPU.getLOGIN());
			Ag.setAge_pwd(AdmPU.getPSW());
			Ag.setAgerefe(AdmPU.getDIS_ADMUSER().getDIS_USE_MATRICULE());
			Ag.setAge_name(AdmPU.getDIS_ADMUSER().getDIS_USE_FNAME()+AdmPU.getDIS_ADMUSER().getDIS_USE_LNAME());
			System.out.println(Ag);
			AgentServ.save(Ag);
			System.out.println(AdmPU);
          return new ResponseEntity<>(AdmPU, HttpStatus.OK);
		} catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
    }
	 @GetMapping({"/getUserProfiles"})
	 public ResponseEntity<List<DisAdmUserProfile>> retirerAllUsers( ){
		try {
		  List<DisAdmUserProfile> usersP = AdmUPRepo.findAll();
		  return new ResponseEntity<>(usersP, HttpStatus.OK);
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	 @GetMapping("/getUserProfileById/{id}")
	 public ResponseEntity<DisAdmUserProfile> getById(@PathVariable("id") Long id) {
	   Optional<DisAdmUserProfile> user = AdmUPRepo.findById(id);
	   if (user.isPresent()) {
	     return new ResponseEntity<>(user.get(), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 
	 @GetMapping("/getProfileById/{id}")
	 public ResponseEntity<String> getppById(@PathVariable("id") Long id) {
	   Optional<DisAdmUserProfile> user = AdmUPRepo.findById(id);
	   String prof ="" ;
	   if (user.isPresent()) {
		   DisAdmUserProfile us=user.get();
		   for(DisAdmProfile p : us.getDIS_ADMPROFILE())
		   {
			   prof+= p.getDIS_PRU_LABEL();
			   
		   }
	     return new ResponseEntity<>(prof , HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 
	 
	 @GetMapping("/getbyUserId/{id}")
	 public ResponseEntity<DisAdmUserProfile> getByUserId(@PathVariable("id") Long id) {
	   Optional<DisAdmUserProfile> user = AdmUPRepo.findbyuser(id);
	   if (user.isPresent()) {
	     return new ResponseEntity<>(user.get(), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 
	
	 
	 @GetMapping({"/getAdmins"})
	 public ResponseEntity<List<DisAdmUserProfile>> retirerAllAdmins( ){
		try {
			
		 
			String caissier="administrateur";
			List<DisAdmUserProfile> users = AdmUPRepo.findbyprofile(caissier);
		  return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	 
	 @GetMapping({"/getCaissiers"})
	 public ResponseEntity<List<DisAdmUserProfile>> retirerAllCaissiers( ){
		try {

		String caissier="caissier";
		List<DisAdmUserProfile> users = AdmUPRepo.findbyprofile(caissier);
		  return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	 
	 @GetMapping({"/getChefs"})
	 public ResponseEntity<List<DisAdmUserProfile>> retirerAllChefs( ){
		try {

			String caissier="chef_hiérarchie";
			List<DisAdmUserProfile> users = AdmUPRepo.findbyprofile(caissier);
		  return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	 
	 @DeleteMapping("/deleteUP/{id}")
	 public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
	   try {
		   AdmUPRepo.deleteById(id);
			System.out.println("deleted");

	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }
	
}
