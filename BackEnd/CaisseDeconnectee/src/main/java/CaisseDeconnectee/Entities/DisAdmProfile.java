package CaisseDeconnectee.Entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection="DisAdmProfile")

public class DisAdmProfile {
	
	@Transient
    public static final String SEQUENCE_NAME = "p_sequence";
	
	@Id
	private Long  DIS_PRU_ID ; //code profile
	
	private Profile DIS_PRU_LABEL ; // label profile 
	
	//TODO : enum etat
	private String DIS_PRU_STATUS ; // etat du profile 
	
	private Date DIS_PRU_UPDTPRUDT ; //date de modification de statuts de profil
	
	private String DIS_PRU_NATUSP ; // nature de profile
	
	private int DIS_PRU_ADMLEVEL ; //niveau administratif
	
	private Date DIS_PRU_CREDT ; // date de création
	
	private Date DIS_PRU_UPDTDT ; //date de dernière modification 


	

}
