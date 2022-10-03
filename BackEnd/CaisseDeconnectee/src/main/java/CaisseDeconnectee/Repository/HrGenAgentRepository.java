package CaisseDeconnectee.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import CaisseDeconnectee.Entities.HrGenAgent;

@Repository
public interface HrGenAgentRepository extends MongoRepository<HrGenAgent, Long > {
	public HrGenAgent findByagerefe(String ref ); 

}
