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
@Document(collection="HrGenAgent")
public class HrGenAgent {
	
	@Transient
    public static final String SEQUENCE_NAME = "agent_sequence";
	
	@Id
	private long age_id;
	
	private String agerefe;
	
	private String age_name;
	
	private String age_login;
	
	private String age_pwd;
	
}
