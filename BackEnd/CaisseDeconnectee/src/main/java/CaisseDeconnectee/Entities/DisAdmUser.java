package CaisseDeconnectee.Entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="DisAdmUser")
public class DisAdmUser {
	
	@Transient
    public static final String SEQUENCE_NAME = "U_sequence";

	@Id
	private Long DIS_USE_ID ; //ID de l'agent connecté
	
	private String DIS_USE_MATRICULE ; //matricule d'utilisateur
	
	private String DIS_USE_LOGIN ; //login applicatif de l'utilisateur
	
	private String DIS_USE_PSW ; // mot de passe de l'utilisateur 
	
	private String DIS_USE_FNAME ; //prenom de l'utilisateur

	private String DIS_USE_LNAME ; //nom de l'utilisateur
		
	private int DIS_USE_NBESSAI ;//nombre de tentatives lors de l'authentification
	
	//TODO: enum statut 
	private String DIS_USE_CPTESTATUS ; //  statut du compte enum to do
	
	private Date DIS_USE_CRTDT ; //date de création du compte
	
	private Date DIS_USE_LASTCNX ; // Dernière connexion 
	
	private String DIS_USE_NIVADM ; //niveau administratif de l'utilisateur
	
	private String DIS_USE_TYPE ; //type d'utilisateur
	
	private boolean DIS_USE_STATUS ; // utilisateur actif (oui, non)

}
