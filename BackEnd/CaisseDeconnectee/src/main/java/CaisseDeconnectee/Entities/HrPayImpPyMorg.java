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
@Document(collection="HrPayImpPyMorg")

public class HrPayImpPyMorg {
	//mode de règlement
	
	@Transient
    public static final String SEQUENCE_NAME = "PIPM_sequence";

	@Id
	private long pyim_id;
	
	private int pyo_id;
	
	private String pym_code;
	
	private String pym_name;
	
	private long CAH_ID ;
}
