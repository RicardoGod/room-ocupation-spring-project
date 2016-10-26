package corp.adentis.wc.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import corp.adentis.wc.model.entities.Door;

@Repository
public interface DoorRepository extends CrudRepository<Door, Long>{
	
}
