package CaisseDeconnectee.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import CaisseDeconnectee.Repository.DisAdmProfileRepository;

@ComponentScan("CaisseDeconnectee.Controller")
@ComponentScan("CaisseDeconnectee.Service")
@ComponentScan("CaisseDeconnectee.Configuration")
@ComponentScan("CaisseDeconnectee.Util")
@SpringBootApplication
@EnableMongoRepositories("CaisseDeconnectee")
public class CaisseDeconnecteeApplication //implements CommandLineRunner 
{
	@Autowired
	DisAdmProfileRepository AdmPRepo ;
	

	public static void main(String[] args) {
		SpringApplication.run(CaisseDeconnecteeApplication.class, args);
	}
	
	
/*	@Override
    public void run(String... args) throws Exception{
		AdmPRepo.deleteAll();
    	DisAdmProfile p = new DisAdmProfile((long) 1,10,"active",100, null);
    	AdmPRepo.save(p);
    	DisAdmProfile p2 = new DisAdmProfile((long) 2,11,"active",200, null);
    	AdmPRepo.save(p2);
    }*/

}
