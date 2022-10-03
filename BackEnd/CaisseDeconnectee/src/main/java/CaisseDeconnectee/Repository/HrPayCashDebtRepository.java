package CaisseDeconnectee.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import CaisseDeconnectee.Entities.HrPayCashDebt;
@Repository
public interface HrPayCashDebtRepository extends MongoRepository<HrPayCashDebt, Long > {

}
