package CaisseDeconnectee.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CaisseDeconnectee.Entities.HrGenDebt;
import CaisseDeconnectee.Repository.HrGenDebtRepository;

@Service
public class HrGenDebtService {
	
	@Autowired
	private HrGenDebtRepository HGDRepo ;
	
	@Autowired 
	private SequenceGeneratorService SequenceGeneratorService ;  
	  
	public HrGenDebt save (HrGenDebt HGD) {
		HGD.setDeb_id(SequenceGeneratorService.generateSequence(HrGenDebt.SEQUENCE_NAME));

		return HGDRepo.save(HGD);	
	}
	
	
	public  List<HrGenDebt> retirerAll(){
        List<HrGenDebt> HGDs = HGDRepo.findAll();
	  return HGDs ;
	}
	
	
	public HrGenDebt update (HrGenDebt A , long id) {
		Optional<HrGenDebt> u = HGDRepo.findById(id);
		 {
			LocalDate todaysDate = LocalDate.now();
			Date d =Date.from(todaysDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			 HrGenDebt HGD = u.get();
			 HGD.setACO_ID(A.getACO_ID());
			 HGD.setAdresse(A.getAdresse());
			 HGD.setDeb_amountinit(A.getDeb_amountinit());
			 HGD.setDeb_date(A.getDeb_date());
			 HGD.setDeb_duedt(A.getDeb_duedt());
			 HGD.setDeb_printdt(d);
			 HGD.setDebrefe(A.getDebrefe());
			 HGD.setDeb_remb(A.isDeb_remb());
			 HGD.setORG_ID(A.getORG_ID());
			 HGD.setPAR_ID(A.getPAR_ID());
			 HGD.setRed_name(A.getRed_name());
			 HGD.setVow_debtype_libelle(A.getVow_debtype_libelle());
			 HGD.setDEB_AMOUNT_CASH(A.getDEB_AMOUNT_CASH());
			 return HGDRepo.save(HGD) ;
			 }
		
	}	
}
