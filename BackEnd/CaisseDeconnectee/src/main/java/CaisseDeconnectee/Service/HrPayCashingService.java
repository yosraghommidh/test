package CaisseDeconnectee.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CaisseDeconnectee.Entities.HrPayCashing;
import CaisseDeconnectee.Repository.HrPayCashingRepository;

@Service
public class HrPayCashingService {

	@Autowired
	private HrPayCashingRepository HPCDeskRepo ;
	
	@Autowired 
	private SequenceGeneratorService SequenceGeneratorService ;  
	  
	public HrPayCashing save (HrPayCashing HP) {
		HP.setCsh_id(SequenceGeneratorService.generateSequence(HrPayCashing.SEQUENCE_NAME));

		return HPCDeskRepo.save(HP);	
	}
	
	
	public  List<HrPayCashing> retirerAll(){
        List<HrPayCashing> HPs = HPCDeskRepo.findAll();
	  return HPs ;
	}
	
	
	public HrPayCashing update (HrPayCashing A , long id) {
		Optional<HrPayCashing> u = HPCDeskRepo.findById(id);
		 {
			 HrPayCashing HP = u.get();
			 HP.setAGE_ID(A.getAGE_ID());
			 HP.setCAM_ID(A.getCAM_ID());
			 HP.setCsh_amount(A.getCsh_amount());
			 HP.setCsh_bank(A.getCsh_bank());
			 HP.setCsh_bankdocument(A.getCsh_bankdocument());
			 HP.setCsh_date(A.getCsh_date());
			 HP.setCsh_rejet_id(A.getCsh_rejet_id());
			 HP.setCur_id(A.getCur_id());
			 HP.setPAR_ID(A.getPAR_ID());
			 HP.setSli_id(A.getSli_id());
			 HP.setVow_rejmotif(A.getVow_rejmotif());
			 return HPCDeskRepo.save(HP) ;
			 }
		
	}	
	
}
