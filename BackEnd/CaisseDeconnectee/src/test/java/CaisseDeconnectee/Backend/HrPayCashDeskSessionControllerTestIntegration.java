package CaisseDeconnectee.Backend;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import CaisseDeconnectee.Entities.DisAdmUserProfile;
import CaisseDeconnectee.Entities.HrPayCashDesk;
import CaisseDeconnectee.Entities.HrPayCashDeskSession;
import CaisseDeconnectee.Repository.HrPayCashDeskRepository;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = CaisseDeconnecteeApplication.class, 
webEnvironment = WebEnvironment.RANDOM_PORT)
public class HrPayCashDeskSessionControllerTestIntegration {

	@LocalServerPort
	  private int port;
	
	@Autowired 
	HrPayCashDeskRepository caisseRepo;

	 TestRestTemplate restTemplate = new TestRestTemplate();

	 HttpHeaders headers = new HttpHeaders();	  
	 

	    @Test
	    @Order(1)
	    public void testRetrieveStudentCourse() throws JSONException {

	        HttpEntity<String> entity = new HttpEntity<>(null, headers);

	        ResponseEntity<String> response = restTemplate.exchange(
	                createURLWithPort("/Facture/getPCDeskSessionById/1"),
	                HttpMethod.GET, entity, String.class);

	        String expected ="{"
	        		+ "  \"css_id\": 1,\r\n"
	        		+ "  \"css_startdt\": \"2022-09-18T10:40:28.126\",\r\n"
	        		+ "  \"css_enddt\": \"2022-09-19T23:40:28.126\",\r\n"
	        		+ "  \"mnt_initial\": 0,\r\n"
	        		+ "  \"cah_ID\": {\r\n"
	        		+ "    \"cah_id\": 1,\r\n"
	        		+ "    \"cah_code\": \"string\",\r\n"
	        		+ "    \"cah_name\": \"string\",\r\n"
	        		+ "    \"cah_maxtrans\": \"string\",\r\n"
	        		+ "    \"cah_internal\": true,\r\n"
	        		+ "    \"cah_dayopen\": true\r\n"
	        		+ "  },\r\n"
	        		+ "  \"ageid\": 0\r\n"
	        		+ "}" ;
	       
	        JSONAssert.assertEquals(expected, response.getBody(), false);
	    }

	    private String createURLWithPort(String uri) {
	        return "http://localhost:" + port + uri;
	    }	
	    
	    @Test
	    @Order(2)
		public void addCourse() {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		
			HrPayCashDesk caiss = caisseRepo.findById((long)1).get();
			HttpEntity<HrPayCashDesk> entity = new HttpEntity<HrPayCashDesk>(caiss, headers);

			ResponseEntity<String> response = restTemplate.exchange(
					createURLWithPort("/Facture/addPCDS/2/510/2022-10-21 08:00/2022-10-21 20:00"),
					HttpMethod.POST, entity, String.class);

			HttpStatus actual = response.getStatusCode();
			System.out.println("kharia"+actual);
			Assertions.assertEquals(HttpStatus.CREATED, actual);

		}
	 
}
