package CaisseDeconnectee.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CaisseDeconnectee.Entities.HrGenBank;
import CaisseDeconnectee.Repository.HrGenBankRepository;

@Service
public class HrGenBankService {
	@Autowired
	private HrGenBankRepository GBRepo ;
	
	@Autowired 
	private SequenceGeneratorService SequenceGeneratorService ;  
	  
	
	public HrGenBank save (HrGenBank HGB) {
		HGB.setBan_id(SequenceGeneratorService.generateSequence(HrGenBank.SEQUENCE_NAME));

		return GBRepo.save(HGB);	
	}
	
	
	public  List<HrGenBank> retirerAll(){
        List<HrGenBank> HGBs = GBRepo.findAll();
	  return HGBs ;
	}
	
	
	public HrGenBank update (HrGenBank A , long id) {
		Optional<HrGenBank> u = GBRepo.findById(id);
		 {
			 HrGenBank HGB = u.get();
			 HGB.setBan_bic(A.getBan_bic());
			 HGB.setBan_code(A.getBan_code());
			 HGB.setBan_name(A.getBan_name());
			 return GBRepo.save(HGB) ;
			 }
		
	}	
}
