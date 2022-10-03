package CaisseDeconnectee.Service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CaisseDeconnectee.Entities.DisAdmProfile;
import CaisseDeconnectee.Repository.DisAdmProfileRepository;


@Service
public class DisAdmProfileService {

	@Autowired
	private DisAdmProfileRepository AdmPRepo ;
	
	@Autowired 
	private SequenceGeneratorService SequenceGeneratorService ;
	
	private static final Logger L = LogManager.getLogger(DisAdmProfileService.class);

	public DisAdmProfile saveProfile (DisAdmProfile AdmP) {
		AdmP.setDIS_PRU_ID(SequenceGeneratorService.generateSequence(DisAdmProfile.SEQUENCE_NAME));
		return AdmPRepo.save(AdmP);	
	}
	
	
	public  List<DisAdmProfile> retirerAllProfile(){
        List<DisAdmProfile> profiles = AdmPRepo.findAll();
        for (DisAdmProfile profile : profiles) {
        	L.info("profile+++ :" + profile);
        }
	  return profiles ;
	}
	
	
	public DisAdmProfile updateProfile (DisAdmProfile profile , Long id) {
		Optional<DisAdmProfile> p = AdmPRepo.findById(id);
		 {
			 DisAdmProfile prof = p.get();
			 prof.setDIS_PRU_ADMLEVEL(profile.getDIS_PRU_ADMLEVEL());
			 prof.setDIS_PRU_CREDT(profile.getDIS_PRU_CREDT());
			 prof.setDIS_PRU_LABEL(profile.getDIS_PRU_LABEL());
			 prof.setDIS_PRU_STATUS(profile.getDIS_PRU_STATUS());
			 return AdmPRepo.save(prof) ;
			 }
		
	}

}
