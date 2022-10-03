package CaisseDeconnectee.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import CaisseDeconnectee.Entities.HrPayCashDeskSession;

@Repository
public interface HrPayCashDeskSessionRepository extends MongoRepository<HrPayCashDeskSession, Long > {

	public HrPayCashDeskSession findByAGEID(long id );
}
