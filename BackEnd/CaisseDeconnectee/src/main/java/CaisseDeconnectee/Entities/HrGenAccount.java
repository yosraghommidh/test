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
@Document(collection="HrGenAccount")
public class HrGenAccount {
	
	@Transient
    public static final String SEQUENCE_NAME = "Acc_sequence";

	@Id
	private Long aco_id; //Identifiant du compte client
	
	private String aco_ref ; 
	
	private int aco_amount; //Solde
	
}
