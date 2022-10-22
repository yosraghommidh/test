package CaisseDeconnectee.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection="HrPayCashDesk")

public class HrPayCashDesk {
	//(caisse)
	@Transient
    public static final String SEQUENCE_NAME = "PCDesk_sequence";

	@Id
	private long cah_id;
	
	private String cah_code;
	
	private String cah_name;
	
	private String cah_maxtrans;
	
	private boolean cah_internal; //caisse interne ou externe true interne
	
	private boolean cah_dayopen;
}
