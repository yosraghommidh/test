package CaisseDeconnectee.Backend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import CaisseDeconnectee.Entities.HrParty;
import CaisseDeconnectee.Repository.HrPartyRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HrPartyControllerTestIntegration {

	  @Autowired
	  private MockMvc mockMvc;

	  @Autowired
	  private ObjectMapper objectMapper;

	  @Autowired
	  private HrPartyRepository repo;
	  
	  @Test
	  void registrationWorksThroughAllLayers() throws Exception {
		  HrParty user =HrParty.builder().par_name("yoti").par_refe("y198").address("tunis").build();

		  MvcResult mvcResult=mockMvc.perform(post("http://localhost:8080/Client/addC")
	            .contentType("application/json")
	            .content(objectMapper.writeValueAsString(user)))
	            .andExpect(status().isCreated()).andReturn()
	            ;
	       /* String responseBody = mvcResult.getResponse().getContentAsString().substring(10, 12).;
		    System.out.println("rrrrr"+responseBody);

	    HrParty u = repo.findById((long)responseBody).get();
	    System.out.println("offf"+  u.getPar_name());
	    Assertions.assertEquals("yoti", u.getPar_name());*/
	  }
}
