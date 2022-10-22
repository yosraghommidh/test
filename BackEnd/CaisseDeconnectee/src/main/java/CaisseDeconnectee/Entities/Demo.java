package CaisseDeconnectee.Entities;

import org.springframework.security.access.annotation.Secured;

public class Demo {
	
	  @Secured("ROLE_chef_hiérarchie")
	  public void method()
	  {
	    System.out.println("Method called");
	  
	}
}
