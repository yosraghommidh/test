package CaisseDeconnectee.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import CaisseDeconnectee.Entities.HrPayImpPyMorg;

@Repository
public interface HrPayImpPyMorgRepository extends MongoRepository<HrPayImpPyMorg, Long > {

}
