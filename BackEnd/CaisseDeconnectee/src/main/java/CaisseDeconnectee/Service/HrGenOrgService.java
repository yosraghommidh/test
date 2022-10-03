package CaisseDeconnectee.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CaisseDeconnectee.Entities.HrGenOrg;
import CaisseDeconnectee.Repository.HrGenOrgRepository;

@Service
public class HrGenOrgService {
	
	@Autowired
	private HrGenOrgRepository HGORepo ;
	
	@Autowired 
	private SequenceGeneratorService SequenceGeneratorService ;  
	  
	public HrGenOrg save (HrGenOrg HGO) {
		HGO.setOrg_id(SequenceGeneratorService.generateSequence(HrGenOrg.SEQUENCE_NAME));

		return HGORepo.save(HGO);	
	}
	
	
	public  List<HrGenOrg> retirerAll(){
        List<HrGenOrg> HGOs = HGORepo.findAll();
	  return HGOs ;
	}
	
	
	public HrGenOrg update (HrGenOrg A , long id) {
		Optional<HrGenOrg> u = HGORepo.findById(id);
		 {
			 HrGenOrg HGO = u.get();
			 HGO.setOrg_name(A.getOrg_name());
			 return HGORepo.save(HGO) ;
			 }
		
	}	

}
