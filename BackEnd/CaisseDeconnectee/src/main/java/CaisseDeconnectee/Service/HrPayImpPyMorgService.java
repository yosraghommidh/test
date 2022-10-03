package CaisseDeconnectee.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CaisseDeconnectee.Entities.HrPayImpPyMorg;
import CaisseDeconnectee.Repository.HrPayImpPyMorgRepository;

@Service
public class HrPayImpPyMorgService {
	
	@Autowired
	private HrPayImpPyMorgRepository HPIPMRepo ;
	
	@Autowired 
	private SequenceGeneratorService SequenceGeneratorService ;  
	  
	public HrPayImpPyMorg save (HrPayImpPyMorg HP) {
		HP.setPyim_id(SequenceGeneratorService.generateSequence(HrPayImpPyMorg.SEQUENCE_NAME));

		return HPIPMRepo.save(HP);	
	}
	
	
	public  List<HrPayImpPyMorg> retirerAll(){
        List<HrPayImpPyMorg> HPs = HPIPMRepo.findAll();
	  return HPs ;
	}
	
	
	public HrPayImpPyMorg update (HrPayImpPyMorg A , long id) {
		Optional<HrPayImpPyMorg> u = HPIPMRepo.findById(id);
		 {
			 HrPayImpPyMorg HP = u.get();
			 HP.setCAH_ID(A.getCAH_ID());
			 HP.setPym_code(A.getPym_code());
			 HP.setPym_name(A.getPym_name());
			 HP.setPyo_id(A.getPyo_id());
			 return HPIPMRepo.save(HP) ;
			 }
		
	}	
	

}
