
package CaisseDeconnectee.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import CaisseDeconnectee.Entities.DisAdmProfile;
import CaisseDeconnectee.Entities.DisAdmUserProfile;
import CaisseDeconnectee.Entities.JwtRequest;
import CaisseDeconnectee.Entities.JwtResponse;
import CaisseDeconnectee.Repository.DisAdmUserProfileRepository;
import CaisseDeconnectee.Util.JwtUtil;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private DisAdmUserProfileRepository AdmUPRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
    	String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName, userPassword);
        UserDetails userDetails = loadUserByUsername(userName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);
                return new JwtResponse( newGeneratedToken);
    }
   

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	DisAdmUserProfile user = AdmUPRepo.findByLOGIN(username);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getLOGIN(),
                    user.getPSW(),
                    getAuthority(user)         
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private Set<SimpleGrantedAuthority> getAuthority(DisAdmUserProfile user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        	for (DisAdmProfile p : user.getDIS_ADMPROFILE())
                authorities.add(new SimpleGrantedAuthority("ROLE_" + p.getDIS_PRU_LABEL()));
        return authorities;
    }

    
    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    
 
}
