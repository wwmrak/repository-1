package repo;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import entity.RazlikovnaTablica;


@Repository
public interface RazlikovnaTablicaRepository extends CrudRepository<RazlikovnaTablica, Integer>{
	
	
}