package CaisseDeconnectee.Entities;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="HrPayCashDeskMove")
public class HrPayCashDeskMove {
	
	@Transient
    public static final String SEQUENCE_NAME = "PCDeskM_sequence";

	@Id
	private long cam_id;	//identifiant du mouvement caisse
	
	private String cam_refe; //référence de l'encaissement
	
	private LocalDateTime cam_date; //date de mouvement=encaissement
	
	private int cam_amount;	//montant de mouvement
	
	//private int vom_camtp;	//type de transaction de caisse
	
	private HrPayCashDeskSession CSS_ID ; 	//id session
}
