package CaisseDeconnectee.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CaisseDeconnectee.Entities.HrPayCashDeskSession;
import CaisseDeconnectee.Repository.HrPayCashDeskSessionRepository;

@Service
public class HrPayCashDeskSessionService {
	
	@Autowired
	private HrPayCashDeskSessionRepository HPCDeskSRepo ;
	
	@Autowired 
	private SequenceGeneratorService SequenceGeneratorService ;  
	  
	public HrPayCashDeskSession save (HrPayCashDeskSession HP) {
		HP.setCss_id(SequenceGeneratorService.generateSequence(HrPayCashDeskSession.SEQUENCE_NAME));

		return HPCDeskSRepo.save(HP);	
	}
	
	
	public  List<HrPayCashDeskSession> retirerAll(){
        List<HrPayCashDeskSession> HPs = HPCDeskSRepo.findAll();
	  return HPs ;
	}
	
	public  HrPayCashDeskSession retreiveOne(long id){
       HrPayCashDeskSession HP = HPCDeskSRepo.findById(id).get();
	  return HP ;
	}
	
	public HrPayCashDeskSession update (HrPayCashDeskSession A , long id) {
		Optional<HrPayCashDeskSession> u = HPCDeskSRepo.findById(id);
		 {
			 HrPayCashDeskSession HP = u.get();
			 HP.setAGEID(A.getAGEID());
			 HP.setCAH_ID(A.getCAH_ID());
			 HP.setCss_enddt(A.getCss_enddt());
			 HP.setCss_startdt(A.getCss_startdt());
			 return HPCDeskSRepo.save(HP) ;
			 }
		
	}	
	

}
