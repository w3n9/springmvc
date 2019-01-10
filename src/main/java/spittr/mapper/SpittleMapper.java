package spittr.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import spittr.entity.Spittle;

import java.util.List;

@Repository
public interface SpittleMapper {
	Spittle findOne(int id);
	List<Spittle> findSpittles(
			@Param("before") int before,
			@Param("count") int count
	);
	void save(Spittle spittle);
}
