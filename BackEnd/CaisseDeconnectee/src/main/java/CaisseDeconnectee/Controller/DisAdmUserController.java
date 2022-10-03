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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import CaisseDeconnectee.Entities.DisAdmUser;
import CaisseDeconnectee.Repository.DisAdmUserRepository;
import CaisseDeconnectee.Service.DisAdmUserService;
import CaisseDeconnectee.Service.SequenceGeneratorService;

@RestController
@RequestMapping("/User")
@CrossOrigin("http://localhost:8080")

public class DisAdmUserController {

	@Autowired 
	private DisAdmUserRepository AdmURepo ;
	
	@Autowired 
	private DisAdmUserService AdmUServ;
	
	@Autowired 
	SequenceGeneratorService SequenceGeneratorService ;
	
	
	@PostMapping({"/addUser"})
    public ResponseEntity<DisAdmUser> save(@RequestBody DisAdmUser user) {
	try {
		  user.setDIS_USE_ID(SequenceGeneratorService.generateSequence(DisAdmUser.SEQUENCE_NAME));
          DisAdmUser u = AdmUServ.saveUser(user);
          return new ResponseEntity<>(u, HttpStatus.CREATED);
		} catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
    }
	
	
	 @GetMapping({"/getUsers"})
	 public ResponseEntity<List<DisAdmUser>> retirerAllUsers( ){
		try {
		  List<DisAdmUser> users = AdmUServ.retirerAllUsers();
		  return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	 
	 
	 @GetMapping("/getUserById/{id}")
	 public ResponseEntity<DisAdmUser> getById(@PathVariable("id") Long id) {
	   Optional<DisAdmUser> user = AdmURepo.findById(id);
	   if (user.isPresent()) {
	     return new ResponseEntity<>(user.get(), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 
	 
	 @PutMapping("/updateUser/{id}")
	 public ResponseEntity<DisAdmUser> update(@PathVariable("id") Long id, @RequestBody DisAdmUser user) {
	   Optional<DisAdmUser> u = AdmURepo.findById(id);
	   if (u.isPresent()) {
	     return new ResponseEntity<>(AdmUServ.updateUser(user, id), HttpStatus.OK);
	   } else {
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	 }
	 

	 @DeleteMapping("/deleteUser/{id}")
	 public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
	   try {
	     AdmURepo.deleteById(id);
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }
	 
	 
	 @DeleteMapping("/deleteUsers")
	 public ResponseEntity<HttpStatus> deleteAll() {
	   try {
	     AdmURepo.deleteAll();
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   } catch (Exception e) {
	     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	 }
	 
	 @GetMapping("/uexists/")
		@ResponseBody
		public DisAdmUser exist(@RequestParam String login, @RequestParam String pwd) {
			return this.AdmUServ.exist(login,pwd);
		}

}
