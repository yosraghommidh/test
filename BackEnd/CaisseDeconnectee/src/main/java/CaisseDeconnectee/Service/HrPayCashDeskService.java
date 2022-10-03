package CaisseDeconnectee.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CaisseDeconnectee.Entities.HrPayCashDesk;
import CaisseDeconnectee.Repository.HrPayCashDeskRepository;
@Service
public class HrPayCashDeskService {
	
	@Autowired
	private HrPayCashDeskRepository HPCDeskRepo ;
	
	@Autowired 
	private SequenceGeneratorService SequenceGeneratorService ;  
	  
	public HrPayCashDesk save (HrPayCashDesk HP) {
		HP.setCah_id(SequenceGeneratorService.generateSequence(HrPayCashDesk.SEQUENCE_NAME));

		return HPCDeskRepo.save(HP);	
	}
	
	
	public  List<HrPayCashDesk> retirerAll(){
        List<HrPayCashDesk> HPs = HPCDeskRepo.findAll();
	  return HPs ;
	}
	
	
	public HrPayCashDesk update (HrPayCashDesk A , long id) {
		Optional<HrPayCashDesk> u = HPCDeskRepo.findById(id);
		 {
			 HrPayCashDesk HP = u.get();
			 HP.setCah_code(A.getCah_code());
			 HP.setCah_dayopen(A.isCah_dayopen());
			 HP.setCah_internal(A.isCah_internal());
			 HP.setCah_maxtrans(A.getCah_maxtrans());
			 HP.setCah_name(A.getCah_name());
			 return HPCDeskRepo.save(HP) ;
			 }
		
	}	

}
