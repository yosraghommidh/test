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
@Document(collection="HrGenOrg")
public class HrGenOrg {
	
	@Transient
    public static final String SEQUENCE_NAME = "Org_sequence";

	@Id
	private long org_id;
	
	private String org_name;
}
