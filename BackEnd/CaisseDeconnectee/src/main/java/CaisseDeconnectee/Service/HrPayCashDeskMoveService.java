package CaisseDeconnectee.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CaisseDeconnectee.Entities.HrPayCashDeskMove;
import CaisseDeconnectee.Repository.HrPayCashDeskMoveRepository;

@Service
public class HrPayCashDeskMoveService {
	
	@Autowired
	private HrPayCashDeskMoveRepository HPCDMRepo ;
	
	@Autowired 
	private SequenceGeneratorService SequenceGeneratorService ;  
	  
	public HrPayCashDeskMove save (HrPayCashDeskMove HP) {
		HP.setCam_id(SequenceGeneratorService.generateSequence(HrPayCashDeskMove.SEQUENCE_NAME));

		return HPCDMRepo.save(HP);	
	}
	
	
	public  List<HrPayCashDeskMove> retirerAll(){
        List<HrPayCashDeskMove> HPs = HPCDMRepo.findAll();
	  return HPs ;
	}
	
	
	public HrPayCashDeskMove update (HrPayCashDeskMove A , long id) {
		Optional<HrPayCashDeskMove> u = HPCDMRepo.findById(id);
		 {
			 HrPayCashDeskMove HP = u.get();
			 HP.setCam_amount(A.getCam_amount());
			 HP.setCam_date(A.getCam_date());
			 HP.setCam_refe(A.getCam_refe());
			 HP.setCSS_ID(A.getCSS_ID());
			 //HP.setVom_camtp(A.getVom_camtp());
			 return HPCDMRepo.save(HP) ;
			 }
		
	}	

}
