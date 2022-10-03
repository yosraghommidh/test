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
@Document(collection="HrAgrServiceAgr")

public class HrAgrServiceAgr {
	
	@Transient
    public static final String SEQUENCE_NAME = "ASA_sequence";

	@Id
	private Long sag_id;// id contrat
	
	private String sag_refe;
}
