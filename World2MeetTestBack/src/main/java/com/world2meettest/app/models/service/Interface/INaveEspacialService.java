package com.world2meettest.app.models.service.Interface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.world2meettest.app.models.entity.NaveEspacial;

public interface INaveEspacialService {

	public NaveEspacial save(NaveEspacial naveEspacial);

	public void deleteNaveEspacialById(Long idNaveEspacial);

	public Page<NaveEspacial> findAllPageable(Pageable pageable);

	public NaveEspacial findNaveEspacialById(Long idNaveEspacial);

	public List<NaveEspacial> findNaveEspacialByName(String name);
}
