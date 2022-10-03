package CaisseDeconnectee.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import CaisseDeconnectee.Entities.HrGenOrg;

@Repository
public interface HrGenOrgRepository extends MongoRepository<HrGenOrg, Long > {

}
