package CaisseDeconnectee.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import CaisseDeconnectee.Entities.HrGenAccount;
import CaisseDeconnectee.Entities.HrGenDebt;
@Repository
public interface HrGenDebtRepository extends MongoRepository<HrGenDebt, Long > {
	
	
	@Query("{'PAR_ID._id':?0}}")
	public List<HrGenDebt> findbyCid(long s);
	
	@Query("{'ACO_ID._id':?0}}")
	public List<HrGenDebt> findbyCompteid(long s);
	
	@Query("{ 'PAR_ID.par_refe' : { $regex: ?0 } }")
	public List<HrGenDebt> findbyClientref(String s);
	
	@Query("{ 'ACO_ID.aco_ref' : { $regex: ?0 } }")
	public List<HrGenDebt> findbyCompteref(String s);

	
	public List<HrGenDebt> findBydebrefeStartsWith(String ref );
	
	@Query("{'PAR_ID.par_account': { $elemMatch: { '_id' : ?0 } }}")
	public HrGenAccount findAccid(long s);
	

}
