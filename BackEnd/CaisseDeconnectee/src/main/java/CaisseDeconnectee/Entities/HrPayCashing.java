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
@Document(collection="HrPayCashing")
public class HrPayCashing {
	
	@Transient
    public static final String SEQUENCE_NAME = "PCash_sequence";

	@Id
	private long csh_id;
	
	private int csh_rejet_id;
	
	private LocalDateTime csh_date;
	
	private int sli_id;
	
	private int csh_amount;
	
	private int cur_id;
	
	private String csh_bankdocument;
	
	private String csh_bank;
	
	private int vow_rejmotif;
	
	private HrParty PAR_ID ;
	
	private HrGenAgent AGE_ID ; 
	
	private HrPayCashDeskMove CAM_ID ;
	
	private HrPayImpPyMorg PYM_ID ;
	
}
