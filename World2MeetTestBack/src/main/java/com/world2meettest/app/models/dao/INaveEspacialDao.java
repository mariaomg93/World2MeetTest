package com.world2meettest.app.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.world2meettest.app.models.entity.NaveEspacial;

public interface INaveEspacialDao extends CrudRepository<NaveEspacial, Long> {

	@Query(value = "select * from naves_espaciales", nativeQuery = true)
	public Page<NaveEspacial> findAllPageable(Pageable pageable);

	@Query(value = " select * from naves_espaciales where upper(nombre_nave_espacial) like %:name%", nativeQuery = true)
	public List<NaveEspacial> findNaveEspacialByName(String name);

}
