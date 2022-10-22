package CaisseDeconnectee.Backend;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import CaisseDeconnectee.Entities.HrPayCashDeskSession;
import CaisseDeconnectee.Entities.JwtRequest;
import CaisseDeconnectee.Entities.JwtResponse;
import CaisseDeconnectee.Repository.HrPayCashDeskRepository;
import CaisseDeconnectee.Service.HrPayCashDeskSessionService;
import CaisseDeconnectee.Service.JwtService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class JwtServiceTest {
	
	@Autowired 
	JwtService jwtService ;
	
	
	
	@Test
	@Order(1)
	public void TestcreateJwtToken() throws Exception 
	{
		JwtResponse jwt = jwtService.createJwtToken(JwtRequest.builder().userName("popo").userPassword("poo").build());
		Assertions.assertNotNull(jwt);
	}
	
	@Test
	@Order(2)
	public void TestloadUserByUsername() {
		UserDetails u= jwtService.loadUserByUsername("yossra_ghommidh");
		String role = u.getAuthorities().toString();
		Assertions.assertEquals("[ROLE_caissier]", role);
	}
	
}
