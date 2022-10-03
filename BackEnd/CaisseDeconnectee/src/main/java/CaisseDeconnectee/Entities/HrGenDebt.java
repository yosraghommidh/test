package CaisseDeconnectee.Entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="HrGenDebt")
public class HrGenDebt //facture 
{
	
	@Transient
    public static final String SEQUENCE_NAME = "Debt_sequence";

	@Id
	private long deb_id;
	
	private String debrefe;	//Référence dette
	
	private String adresse;	//adresse de facturation
	
	@DateTimeFormat(style="yyyy-MM-dd")
	private Date deb_date; //date d'officialisation

	@DateTimeFormat(style="yyyy-MM-dd")
	private Date deb_duedt; //date d'exigibilité
	
	@DateTimeFormat(style="yyyy-MM-dd")
	private Date deb_printdt; //date d'édition de la facture
	
	private int deb_amountinit; //Montant de la facture

	private int DEB_NORECOVERY ; // Dette exonérée de relance 
	
	private int DEB_AMOUNT_CASH ; // montant d'encaissement 
	
	private String vow_debtype_libelle; //type de la facture
	
	private boolean deb_remb; //dette remboursable ou non
	
	private String run_name; //nom du train de facturation
	
	private String red_name; //étape actuel de relance
	
	private HrParty PAR_ID ; //Client destinataire de la factureéé
	
	private HrGenOrg ORG_ID ; //identifiant de l'organisation
	
	private HrGenAccount ACO_ID ; //Compte sur lequel est la dette
}
