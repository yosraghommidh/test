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

import CaisseDeconnectee.Entities.DisAdmProfile;
import CaisseDeconnectee.Repository.DisAdmProfileRepository;
import CaisseDeconnectee.Service.DisAdmProfileService;
import CaisseDeconnectee.Service.SequenceGeneratorService;



@RestController
@RequestMapping("/Profile")
@CrossOrigin("http://localhost:8080")
public class DisAdmProfileController {

	@Autowired 
	private DisAdmProfileRepository AdmPRepo ;
	
	@Autowired 
	private DisAdmProfileService AdmPServ;
	
	@Autowired 
	SequenceGeneratorService SequenceGeneratorService ;

   
	@PostMapping({"/addProfil"})
    public ResponseEntity<DisAdmProfile> saveProfile(@RequestBody DisAdmProfile profile) {
	try {
		  profile.setDIS_PRU_ID(SequenceGeneratorService.generateSequence(DisAdmProfile.SEQUENCE_NAME));
          DisAdmProfile prof = AdmPServ.saveProfile(profile);
          return new ResponseEntity<>(prof, HttpStatus.CREATED);
		} catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
    }
	
	
	 @GetMapping({"/getProfiles"})
	 public ResponseEntity<List<DisAdmProfile>> retirerAllProfile( ){
		try {
		  List<DisAdmProfile> profiles = AdmPRepo.findAll() ;
		  return new ResponseEntity<>(profiles, HttpStatus.OK);
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	 
	 
	 @GetMapping("/getProfilesById/{id}")
	 public ResponseEntity<DisAdmProfile> getProfileById(@PathVariable("id") Long id) {
	   Optional<DisAdmProfile> profile = AdmPRepo.findById(id);
	   if (profile.isPresent()) {
	     return new ResponseEntity<>(profile.get(), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 
	 
	 @PutMapping("/updateProfile/{id}")
	 public ResponseEntity<DisAdmProfile> updateprofile(@PathVariable("id") Long id, @RequestBody DisAdmProfile profile) {
	   Optional<DisAdmProfile> p = AdmPRepo.findById(id);
	   if (p.isPresent()) {
	     return new ResponseEntity<>(AdmPServ.updateProfile(profile, id), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 

	 @DeleteMapping("/deleteProfile/{id}")
	 public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
	   try {
	     AdmPRepo.deleteById(id);
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }
	 
	 
	 @DeleteMapping("/deleteProfiles")
	 public ResponseEntity<HttpStatus> deleteAll() {
	   try {
	     AdmPRepo.deleteAll();
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }
	 
}
