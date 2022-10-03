package CaisseDeconnectee.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import CaisseDeconnectee.Entities.DisAdmProfile;

@Repository
public interface DisAdmProfileRepository extends MongoRepository<DisAdmProfile, Long >  {
	
}
