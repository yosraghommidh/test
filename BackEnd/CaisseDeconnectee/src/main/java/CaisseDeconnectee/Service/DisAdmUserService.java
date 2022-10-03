package CaisseDeconnectee.Service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CaisseDeconnectee.Entities.DisAdmUser;
import CaisseDeconnectee.Repository.DisAdmUserRepository;

@Service
public class DisAdmUserService {

	@Autowired
	private DisAdmUserRepository AdmURepo ;
	
	@Autowired 
	private SequenceGeneratorService SequenceGeneratorService ;
	
	private static final Logger L = LogManager.getLogger(DisAdmUserService.class);
  
	public DisAdmUser saveUser (DisAdmUser AdmU) {
		AdmU.setDIS_USE_ID(SequenceGeneratorService.generateSequence(DisAdmUser.SEQUENCE_NAME));
		return AdmURepo.save(AdmU);	
	}
	
	
	public  List<DisAdmUser> retirerAllUsers(){
        List<DisAdmUser> users = AdmURepo.findAll();
        for (DisAdmUser user : users) {
        	L.info("user+++ :" + user);
        }
	  return users ;
	}
	
	
	public DisAdmUser updateUser (DisAdmUser user , Long id) {
		Optional<DisAdmUser> u = AdmURepo.findById(id);
		 {
			 DisAdmUser AdmU = u.get();
			 AdmU.setDIS_USE_MATRICULE(user.getDIS_USE_MATRICULE());
			 AdmU.setDIS_USE_LOGIN(user.getDIS_USE_LOGIN());
			 AdmU.setDIS_USE_PSW(user.getDIS_USE_PSW());
			 AdmU.setDIS_USE_FNAME(user.getDIS_USE_FNAME());
			 AdmU.setDIS_USE_LNAME(user.getDIS_USE_LNAME());
			 AdmU.setDIS_USE_NBESSAI(user.getDIS_USE_NBESSAI());
			 AdmU.setDIS_USE_CPTESTATUS(user.getDIS_USE_CPTESTATUS());
			 AdmU.setDIS_USE_CRTDT(user.getDIS_USE_CRTDT());
			 AdmU.setDIS_USE_LASTCNX(user.getDIS_USE_LASTCNX());
			 AdmU.setDIS_USE_NIVADM(user.getDIS_USE_NIVADM());
			 AdmU.setDIS_USE_TYPE(user.getDIS_USE_TYPE());
			 AdmU.setDIS_USE_STATUS(user.isDIS_USE_STATUS());
			 return AdmURepo.save(AdmU) ;
			 }
		
	}	
	
	
	public DisAdmUser exist(String login, String pwd) {
		DisAdmUser user=new DisAdmUser();
		for(DisAdmUser u : (List<DisAdmUser>) AdmURepo.findAll()){
			if(u.getDIS_USE_LOGIN().equals(login) && u.getDIS_USE_PSW().equals(pwd)){
				user=u;
				return user;
			}
		}
		return null;
	}
	
}
