package CaisseDeconnectee.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CaisseDeconnectee.Entities.HrGenAccount;
import CaisseDeconnectee.Repository.HrGenAccountRepository;

@Service
public class HrGenAccountService {
	@Autowired
	private HrGenAccountRepository AccRepo ;
	
	@Autowired 
	private SequenceGeneratorService SequenceGeneratorService ;  
	  
	public HrGenAccount save (HrGenAccount HP) {
		HP.setAco_id(SequenceGeneratorService.generateSequence(HrGenAccount.SEQUENCE_NAME));

		return AccRepo.save(HP);	
	}
	
	
	public  List<HrGenAccount> retirerAll(){
        List<HrGenAccount> HPs = AccRepo.findAll();
	  return HPs ;
	}
	
	
	public HrGenAccount update (HrGenAccount A , long id) {
		Optional<HrGenAccount> u = AccRepo.findById(id);
		 {
			 HrGenAccount HP = u.get();
			 HP.setAco_amount(A.getAco_amount());
			 return AccRepo.save(HP) ;
			 }
		
	}	
	public HrGenAccount updatesolde (int solde , long id) {
		Optional<HrGenAccount> u = AccRepo.findById(id);
		 {
			 HrGenAccount HP = u.get();
			 HP.setAco_amount(solde);
			 return AccRepo.save(HP) ;
			 }
		
	}	
}
