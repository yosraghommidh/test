package CaisseDeconnectee.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CaisseDeconnectee.Entities.DisAdmProfile;
import CaisseDeconnectee.Entities.DisAdmUserProfile;
import CaisseDeconnectee.Entities.HrGenAgent;
import CaisseDeconnectee.Entities.JwtRequest;
import CaisseDeconnectee.Entities.JwtResponse;
import CaisseDeconnectee.Entities.Profile;
import CaisseDeconnectee.Repository.DisAdmUserProfileRepository;
import CaisseDeconnectee.Repository.HrGenAgentRepository;
import CaisseDeconnectee.Service.JwtService;
import CaisseDeconnectee.Util.JwtUtil;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/Auth")
public class JwtController {

    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private DisAdmUserProfileRepository repo ;
    
    
    @Autowired
    private HrGenAgentRepository Agrepo ;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
    	
    	JwtResponse r =jwtService.createJwtToken(jwtRequest);
    	System.out.println(r.getJwtToken());
    	DisAdmUserProfile user = repo.findByLOGIN(jwtRequest.getUserName());
    	Set<SimpleGrantedAuthority> role =jwtService.getAuthority(user);
    	String rl =role.toString();
    	r.setRole(rl);
        return r;
    }
    @GetMapping({"/getUserNameJWT/{jwt}"})
	 public String getUserNameFromToken(@PathVariable("jwt") String Token ){
			String user =jwtUtil.getUsernameFromToken(Token);
		  return user;
	}
    @GetMapping({"/getUserJWT/{jwt}"})
	 public DisAdmUserProfile getUserIdFromToken(@PathVariable("jwt") String Token ){
    	String user =jwtUtil.getUsernameFromToken(Token);
    	DisAdmUserProfile up = repo.findbyusername(user);
		  return up ;
	}
    
    @GetMapping({"/getAgentId/{jwt}"})
	 public long getAgentIdFromToken(@PathVariable("jwt") String Token ){
    	String user =jwtUtil.getUsernameFromToken(Token);
    	DisAdmUserProfile up = repo.findbyusername(user);
		String mat = up.getDIS_ADMUSER().getDIS_USE_MATRICULE();
		HrGenAgent Ag = Agrepo.findByagerefe(mat);
		return Ag.getAge_id();
	}
    
    
    @GetMapping({"/getUserFLJWT/{jwt}"})
	 public String getUserFLFromToken(@PathVariable("jwt")String Token ){
    	String user =jwtUtil.getUsernameFromToken(Token);
    	DisAdmUserProfile up = repo.findbyusername(user);
    	String S = up.getDIS_ADMUSER().getDIS_USE_FNAME() +" "+ 
    	up.getDIS_ADMUSER().getDIS_USE_LNAME().toUpperCase();
		  return S ;
	}
    
    @GetMapping({"/getUserRoleJWT/{jwt}"})
 	 public int getUserRoleFromToken(@PathVariable("jwt")String Token ){
    	DisAdmUserProfile up = getUserIdFromToken(Token) ;
    	List<DisAdmProfile> lp =up.getDIS_ADMPROFILE();
    	if (lp.size() ==1)
    	{
    		if (lp.get(0).getDIS_PRU_LABEL()==Profile.caissier) return 1 ;
    		if (lp.get(0).getDIS_PRU_LABEL()==Profile.chef_hiérarchie) return 2 ;
    		if (lp.get(0).getDIS_PRU_LABEL()==Profile.administrateur) return 3 ;
    	}
    	if (lp.size() ==2)
    	{
    		if( (lp.get(0).getDIS_PRU_LABEL()==Profile.caissier || lp.get(0).getDIS_PRU_LABEL()==Profile.chef_hiérarchie)
    				&&  (lp.get(1).getDIS_PRU_LABEL()==Profile.chef_hiérarchie) || lp.get(1).getDIS_PRU_LABEL()==Profile.caissier)
    			return 4 ;
    		if( (lp.get(0).getDIS_PRU_LABEL()==Profile.caissier || lp.get(0).getDIS_PRU_LABEL()==Profile.administrateur)
    				&&  (lp.get(1).getDIS_PRU_LABEL()==Profile.administrateur) || lp.get(1).getDIS_PRU_LABEL()==Profile.caissier)
    			return 5 ;
    		if( (lp.get(0).getDIS_PRU_LABEL()==Profile.administrateur || lp.get(0).getDIS_PRU_LABEL()==Profile.chef_hiérarchie)
    				&&  (lp.get(1).getDIS_PRU_LABEL()==Profile.chef_hiérarchie) || lp.get(1).getDIS_PRU_LABEL()==Profile.administrateur)
    			return 6 ;
    			}
    	if (lp.size()==3) return 7 ;
		return 0;
 	}
}
