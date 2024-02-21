package com.world2meettest.app.models.service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.world2meettest.app.exception.SpaceShipNotFoundException;
import com.world2meettest.app.models.dao.INaveEspacialDao;
import com.world2meettest.app.models.entity.NaveEspacial;
import com.world2meettest.app.models.service.Interface.INaveEspacialService;

@Service
public class NaveEspacialServiceImpl implements INaveEspacialService {

	@Autowired
	INaveEspacialDao naveEspacialDao;

	@Override
	public NaveEspacial save(NaveEspacial naveEspacial) {
		return naveEspacialDao.save(naveEspacial);
	}

	@Override
	public Page<NaveEspacial> findAllPageable(Pageable pageable) {
		return naveEspacialDao.findAllPageable(pageable);
	}

	@Override
	@Cacheable(value = "NaveEspacial", key = "#idNaveEspacial")
	public NaveEspacial findNaveEspacialById(Long idNaveEspacial) {
		return naveEspacialDao.findById(idNaveEspacial).orElseThrow(
				() -> new SpaceShipNotFoundException("No se ha encontrado ninguna nave espacial con ese ID."));
	}

	@Override
	public List<NaveEspacial> findNaveEspacialByName(String name) {
		return naveEspacialDao.findNaveEspacialByName(name);
	}

	@Override
	public void deleteNaveEspacialById(Long idNaveEspacial) {
		naveEspacialDao.findById(idNaveEspacial).orElseThrow(
				() -> new SpaceShipNotFoundException("No se ha encontrado ninguna nave espacial con ese ID."));
		naveEspacialDao.deleteById(idNaveEspacial);
	}
}
