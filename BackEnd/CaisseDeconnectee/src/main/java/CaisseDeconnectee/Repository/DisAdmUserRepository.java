package CaisseDeconnectee.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import CaisseDeconnectee.Entities.DisAdmUser;

@Repository
public interface DisAdmUserRepository extends MongoRepository<DisAdmUser, Long > {

}
