package CaisseDeconnectee.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import CaisseDeconnectee.Entities.HrGenAccount;

@Repository
public interface HrGenAccountRepository extends MongoRepository<HrGenAccount, Long > {

}
