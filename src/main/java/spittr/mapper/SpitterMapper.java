package spittr.mapper;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import spittr.entity.Spitter;

@Repository
public interface SpitterMapper  {
	void save(Spitter spitter) throws DataAccessException;
	Spitter findByUsername(String username);
}
