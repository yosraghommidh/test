package CaisseDeconnectee.Backend;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import CaisseDeconnectee.Entities.DisAdmUserProfile;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(classes = CaisseDeconnecteeApplication.class, 
webEnvironment = WebEnvironment.RANDOM_PORT)
public class DisAdmUserProfileControllerTestIntegration {
	 @LocalServerPort
	  private int port;
	 

	 TestRestTemplate restTemplate = new TestRestTemplate();

	 HttpHeaders headers = new HttpHeaders();	  
	 

	    @Test
	    public void testRetrieveStudentCourse() throws JSONException {

	        HttpEntity<String> entity = new HttpEntity<>(null, headers);

	        ResponseEntity<String> response = restTemplate.exchange(
	                createURLWithPort("​/UserProfile​/getUserProfileById​/7"),
	                HttpMethod.GET, entity, String.class);

	        String expected ="{"
	        		+ "  \"dis_USP_GRANTED\": true,"
	        		+ "  \"dis_USP_STARTDT\": \"2022-08-11T12:28:52.971+00:00\","
	        		+ "  \"dis_ADMPROFILE\": ["
	        		+ "    {"
	        		+ "      \"dis_PRU_UPDTDT\": \"2022-08-11T12:28:52.971+00:00\","
	        		+ "      \"dis_PRU_NATUSP\": \"string\","
	        		+ "      \"dis_PRU_UPDTPRUDT\": \"2022-08-11T12:28:52.971+00:00\","
	        		+ "      \"dis_PRU_STATUS\": \"string\","
	        		+ "      \"dis_PRU_ADMLEVEL\": 0,"
	        		+ "      \"dis_PRU_ID\": 4,"
	        		+ "      \"dis_PRU_LABEL\": \"chef_hiérarchie\","
	        		+ "      \"dis_PRU_CREDT\": \"2022-08-11T12:28:52.971+00:00\""
	        		+ "    }"
	        		+ "  ],"
	        		+ "  \"dis_USE_ID\": 7,"
	        		+ "  \"psw\": \"$2a$10$PLYf91KuxX513EFk1LmjBO8FBKbWfx93Iz2B2HO3kAXofXrBMJmGy\","
	        		+ "  \"dis_ADMUSER\": {"
	        		+ "    \"dis_USE_MATRICULE\": \"14590\","
	        		+ "    \"dis_USE_LASTCNX\": \"2022-08-11T12:28:52.971+00:00\","
	        		+ "    \"dis_USE_NBESSAI\": 0,"
	        		+ "    \"dis_USE_CPTESTATUS\": \"string\","
	        		+ "    \"dis_USE_STATUS\": true,"
	        		+ "    \"dis_USE_NIVADM\": \"string\","
	        		+ "    \"dis_USE_ID\": 4,"
	        		+ "    \"dis_USE_LNAME\": \"ghommidh\","
	        		+ "    \"dis_USE_FNAME\": \"Amina\","
	        		+ "    \"dis_USE_TYPE\": \"string\","
	        		+ "    \"dis_USE_LOGIN\": \"popo\","
	        		+ "    \"dis_USE_CRTDT\": \"2022-08-11T12:28:52.971+00:00\","
	        		+ "    \"dis_USE_PSW\": \"poo\""
	        		+ "  },"
	        		+ "  \"login\": \"popo\","
	        		+ "  \"dis_USP_ENDDT\": \"2022-08-11T12:28:52.971+00:00\""
	        		+ "}" ;
	       
	        JSONAssert.assertEquals(expected, response.getBody(), false);
	    }

	    private String createURLWithPort(String uri) {
	        return "http://localhost:" + port + uri;
	    }	 
	 
	 
	 
}
