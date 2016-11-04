package corp.adentis.wc.model.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import corp.adentis.wc.model.entities.DoorAverage;

@Repository
public interface DoorAverageRepository extends CrudRepository<DoorAverage, Long>{

	
	@Modifying(clearAutomatically = true)
	@Transactional(propagation=Propagation.REQUIRED)
	@Query("UPDATE DoorAverage dA SET dA.exit_time=:exit_time, dA.elapsed_time=:elapsed_time, dA.average_time=:average_time where dA.id=:id") 
	int setExitEvent(@Param("id") long id, @Param("exit_time") Date exit_time, @Param("elapsed_time") long elapsed_time, @Param("average_time") long average_time);
}
