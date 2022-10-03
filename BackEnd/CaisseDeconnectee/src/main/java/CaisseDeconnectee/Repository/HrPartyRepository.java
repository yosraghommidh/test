package CaisseDeconnectee.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import CaisseDeconnectee.Entities.HrParty;
@Repository
public interface HrPartyRepository extends MongoRepository<HrParty, Long >{

}
