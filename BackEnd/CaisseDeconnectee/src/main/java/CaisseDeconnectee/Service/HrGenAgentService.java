package CaisseDeconnectee.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CaisseDeconnectee.Entities.HrGenAccount;
import CaisseDeconnectee.Entities.HrGenAgent;
import CaisseDeconnectee.Repository.HrGenAgentRepository;

@Service
public class HrGenAgentService {
	@Autowired
	private HrGenAgentRepository AgRepo ;
	
	@Autowired 
	private SequenceGeneratorService SequenceGeneratorService ;  
	  
	public HrGenAgent save (HrGenAgent HGA) {
		return AgRepo.save(HGA);	
	}
	
	
	public  List<HrGenAgent> retirerAll(){
        List<HrGenAgent> HGAs = AgRepo.findAll();
	  return HGAs ;
	}
	
	
	public HrGenAgent update (HrGenAgent A , long id) {
		Optional<HrGenAgent> u = AgRepo.findById(id);
		 {
			 HrGenAgent HGA = u.get();
			 HGA.setAge_login(A.getAge_login());
			 HGA.setAge_name(A.getAge_name());
			 HGA.setAge_pwd(A.getAge_pwd());
			 HGA.setAgerefe(A.getAgerefe());
			 return AgRepo.save(HGA) ;
			 }
		
	}	

}
