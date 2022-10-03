package CaisseDeconnectee.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import CaisseDeconnectee.Entities.HrAgrServiceAgr;

@Repository
public interface HrAgrServiceAgrRepository extends MongoRepository<HrAgrServiceAgr, Long > {

}
