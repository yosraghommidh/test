package CaisseDeconnectee.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CaisseDeconnectee.Entities.HrAgrServiceAgr;
import CaisseDeconnectee.Repository.HrAgrServiceAgrRepository;

@Service
public class HrAgrServiceAgrService {
	@Autowired
	private HrAgrServiceAgrRepository ASRepo ;
	
	@Autowired 
	private SequenceGeneratorService SequenceGeneratorService ;  
	
	public HrAgrServiceAgr save (HrAgrServiceAgr AS) {
		AS.setSag_id(SequenceGeneratorService.generateSequence(HrAgrServiceAgr.SEQUENCE_NAME));

		return ASRepo.save(AS);	
	}
	
	
	public  List<HrAgrServiceAgr> retirerAll(){
        List<HrAgrServiceAgr> Agrs = ASRepo.findAll();
	  return Agrs ;
	}
	
	
	public HrAgrServiceAgr update (HrAgrServiceAgr A , Long id) {
		Optional<HrAgrServiceAgr> u = ASRepo.findById(id);
		 {
			 HrAgrServiceAgr Ags = u.get();
			 Ags.setSag_refe(A.getSag_refe());			 
			 return ASRepo.save(Ags) ;
			 }
		
	}	

}
