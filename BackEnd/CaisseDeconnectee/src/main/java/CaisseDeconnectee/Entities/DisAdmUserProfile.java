package CaisseDeconnectee.Entities;

import java.util.Date;
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
@Document(collection="DisAdmUserProfile")
public class DisAdmUserProfile {
	
	@Transient
    public static final String SEQUENCE_NAME = "UP_sequence";
	
	@Id
	private Long DIS_USE_ID ; // id de la table
	
	private DisAdmUser DIS_ADMUSER ; // user
	
	private List<DisAdmProfile> DIS_ADMPROFILE ; // id profile
	
	private boolean DIS_USP_GRANTED ;
	
	private Date DIS_USP_STARTDT ; // start date
	
	private Date DIS_USP_ENDDT ; // end date 
	
	private String LOGIN ; //login applicatif de l'utilisateur
	
	private String PSW ; // mot de passe de l'utilisateur

}
