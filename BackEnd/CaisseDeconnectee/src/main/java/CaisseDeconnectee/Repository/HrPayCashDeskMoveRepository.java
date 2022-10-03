package CaisseDeconnectee.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import CaisseDeconnectee.Entities.HrPayCashDeskMove;
@Repository
public interface HrPayCashDeskMoveRepository extends MongoRepository<HrPayCashDeskMove, Long > {

}
