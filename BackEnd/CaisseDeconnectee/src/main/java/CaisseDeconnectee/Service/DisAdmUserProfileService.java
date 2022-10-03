package CaisseDeconnectee.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import CaisseDeconnectee.Entities.DisAdmUser;
import CaisseDeconnectee.Entities.DisAdmUserProfile;
import CaisseDeconnectee.Repository.DisAdmUserProfileRepository;

@Service
public class DisAdmUserProfileService {

	@Autowired
	private DisAdmUserProfileRepository AdmUPRepo ;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
    
    
	public DisAdmUserProfile saveUserProfile (DisAdmUserProfile AdmUP) {
		DisAdmUser user = AdmUP.getDIS_ADMUSER();
		AdmUP.setLOGIN(user.getDIS_USE_LOGIN());
		AdmUP.setPSW(getEncodedPassword(user.getDIS_USE_PSW()));
		return AdmUPRepo.save(AdmUP);	
	}
	
}
