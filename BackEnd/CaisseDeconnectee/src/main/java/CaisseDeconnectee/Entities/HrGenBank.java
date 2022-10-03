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
@Document(collection="HrGenBank")
public class HrGenBank {
	
	@Transient
    public static final String SEQUENCE_NAME = "Bank_sequence";

	@Id
	private long ban_id;
	
	private String ban_code;
	
	private String ban_name;
	
	private String ban_bic;

}
