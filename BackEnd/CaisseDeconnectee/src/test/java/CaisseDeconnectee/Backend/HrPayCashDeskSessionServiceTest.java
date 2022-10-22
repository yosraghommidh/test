package CaisseDeconnectee.Backend;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import CaisseDeconnectee.Entities.HrPayCashDeskSession;
import CaisseDeconnectee.Repository.HrPayCashDeskRepository;
import CaisseDeconnectee.Service.HrPayCashDeskSessionService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class HrPayCashDeskSessionServiceTest {
	
	@Autowired 
	HrPayCashDeskSessionService HPDSServ ;
	
	@Autowired 
	HrPayCashDeskRepository caisseRepo;
	
	
	@Test
	@Order(1)
	public void Testsave() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		HrPayCashDeskSession sess = HPDSServ.save( HrPayCashDeskSession.builder().AGEID(2)
				.CAH_ID(caisseRepo.findById((long)1).get())
				.css_startdt(LocalDateTime.parse("2022-10-20 08:00", formatter))
				.css_enddt(LocalDateTime.parse("2022-10-20 20:00", formatter))
				.mnt_initial((long)120)
				.build()
				);
		Assertions.assertNotNull(sess);	
	}
	

	@Test
	@Order(2)
	public void TestretirerAll() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		int listsize = HPDSServ.retirerAll().size();
		HrPayCashDeskSession sess = HPDSServ.save( HrPayCashDeskSession.builder().AGEID(2)
				.CAH_ID(caisseRepo.findById((long)1).get())
				.css_startdt(LocalDateTime.parse("2022-10-20 08:00", formatter))
				.css_enddt(LocalDateTime.parse("2022-10-20 20:00", formatter))
				.mnt_initial((long)120)
				.build()
				);
		Assertions.assertEquals(listsize+1, HPDSServ.retirerAll().size());
	}
	
	
	@Test
	@Order(3)
	public void TestretreiveOne() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		HrPayCashDeskSession sess = HPDSServ.save( HrPayCashDeskSession.builder().AGEID(2)
				.CAH_ID(caisseRepo.findById((long)1).get())
				.css_startdt(LocalDateTime.parse("2022-10-20 08:00", formatter))
				.css_enddt(LocalDateTime.parse("2022-10-20 20:00", formatter))
				.mnt_initial((long)120)
				.build()
				);
		Assertions.assertEquals(sess.getCss_id(), HPDSServ.retreiveOne(sess.getCss_id()).getCss_id());
	}

}
