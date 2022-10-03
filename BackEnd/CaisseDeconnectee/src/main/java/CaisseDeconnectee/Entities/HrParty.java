package CaisseDeconnectee.Entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="HrParty")
public class HrParty {
	
	@Transient
    public static final String SEQUENCE_NAME = "U_sequence";

	@Id
	private long par_id; //identifiant du client
	
	private String par_refe; //référence client
	
	private String par_refe_rxt; //deuxième référence client
	
	private String par_name; //nom complet du client
	
	private String address; //adresse du client
	
	private List<HrGenAccount> par_account ; //liste des comptes client
}
