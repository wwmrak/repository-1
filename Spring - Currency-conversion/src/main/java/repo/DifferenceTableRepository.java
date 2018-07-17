package repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import entity.DifferenceTable;

@Repository
public interface DifferenceTableRepository extends CrudRepository<DifferenceTable, Integer>{
}