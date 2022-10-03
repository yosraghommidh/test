package CaisseDeconnectee.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import CaisseDeconnectee.Entities.DisAdmUserProfile;

@Repository
public interface DisAdmUserProfileRepository extends MongoRepository<DisAdmUserProfile, Long > {

	public DisAdmUserProfile findByLOGIN(String login);
	
	@Query("{'DIS_ADMPROFILE': { $elemMatch: { 'DIS_PRU_LABEL' : ?0 } }}")
	public List<DisAdmUserProfile> findbyprofile(String s);
	
	@Query("{'DIS_ADMUSER.DIS_USE_ID':?0}}")
	public Optional<DisAdmUserProfile> findbyuser(Long s);
	
	@Query("{'LOGIN':?0}}")
	public DisAdmUserProfile findbyusername(String s);
}
