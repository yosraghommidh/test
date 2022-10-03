package CaisseDeconnectee.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import CaisseDeconnectee.Entities.HrGenBank;

@Repository
public interface HrGenBankRepository extends MongoRepository<HrGenBank, Long >  {

}
