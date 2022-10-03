package CaisseDeconnectee.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import CaisseDeconnectee.Entities.HrPayCashing;

@Repository
public interface HrPayCashingRepository extends MongoRepository<HrPayCashing, Long > {

}
