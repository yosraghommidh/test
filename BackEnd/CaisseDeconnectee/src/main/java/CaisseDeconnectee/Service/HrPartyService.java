package CaisseDeconnectee.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CaisseDeconnectee.Entities.HrParty;
import CaisseDeconnectee.Repository.HrPartyRepository;

@Service
public class HrPartyService {
	
	@Autowired
	private HrPartyRepository HPRepo ;
	
	@Autowired 
	private SequenceGeneratorService SequenceGeneratorService ;  
	  
	public HrParty save (HrParty HP) {
		HP.setPar_id(SequenceGeneratorService.generateSequence(HrParty.SEQUENCE_NAME));

		return HPRepo.save(HP);	
	}
	
	
	public  List<HrParty> retirerAll(){
        List<HrParty> HPs = HPRepo.findAll();
	  return HPs ;
	}
	
	
	public HrParty update (HrParty A , long id) {
		Optional<HrParty> u = HPRepo.findById(id);
		 {
			 HrParty HP = u.get();
			 HP.setAddress(A.getAddress()); 
			 HP.setPar_name(A.getPar_name());
			 HP.setPar_refe(A.getPar_refe());
			 HP.setPar_refe_rxt(A.getPar_refe_rxt());
			 HP.setPar_account(A.getPar_account());
			 return HPRepo.save(HP) ;
			 }
		
	}	
}
