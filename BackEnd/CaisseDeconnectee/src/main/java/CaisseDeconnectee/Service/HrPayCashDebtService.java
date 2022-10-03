package CaisseDeconnectee.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CaisseDeconnectee.Entities.HrPayCashDebt;
import CaisseDeconnectee.Repository.HrPayCashDebtRepository;

@Service
public class HrPayCashDebtService {

	@Autowired
	private HrPayCashDebtRepository HPCDRepo ;
	
	@Autowired 
	private SequenceGeneratorService SequenceGeneratorService ;  
	  
	public HrPayCashDebt save (HrPayCashDebt HP) {
		HP.setPcd_id(SequenceGeneratorService.generateSequence(HrPayCashDebt.SEQUENCE_NAME));

		return HPCDRepo.save(HP);	
	}
	
	
	public  List<HrPayCashDebt> retirerAll(){
        List<HrPayCashDebt> HPs = HPCDRepo.findAll();
	  return HPs ;
	}
	
	
	public HrPayCashDebt update (HrPayCashDebt A , long id) {
		Optional<HrPayCashDebt> u = HPCDRepo.findById(id);
		 {
			 HrPayCashDebt HP = u.get();
			 HP.setPcd_date(A.getPcd_date());
			 HP.setPcd_amount(A.getPcd_amount());
			 HP.setDEB_ID(A.getDEB_ID());
			 HP.setCSH_ID(A.getCSH_ID());
			 return HPCDRepo.save(HP) ;
			 }
		
	}	
}
