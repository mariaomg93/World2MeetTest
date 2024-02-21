package com.world2meettest.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.world2meettest.app.controllers.NaveEspacialController;
import com.world2meettest.app.models.entity.NaveEspacial;
import com.world2meettest.app.models.service.Interface.INaveEspacialService;

class NaveEspacialControllerTest {

	INaveEspacialService naveEspacialServiceMock = Mockito.mock(INaveEspacialService.class);

	@Autowired
	NaveEspacialController naveEspacialController = new NaveEspacialController(naveEspacialServiceMock);

	@BeforeEach
	void setUp() throws Exception {
		NaveEspacial naveEspacialMock = new NaveEspacial();
		naveEspacialMock.setIdNaveEspacial((long) 100);
		naveEspacialMock.setNombreNaveEspacial("La mejor");

		Mockito.when(naveEspacialServiceMock.findNaveEspacialById((long) 100)).thenReturn(naveEspacialMock);

	}

	@Test
	void testGetSpaceShipById() {
		ResponseEntity<NaveEspacial> respuestaNavePorId;
		respuestaNavePorId = naveEspacialController.getSpaceShipById((long) 100);
		Assertions.assertEquals("La mejor", respuestaNavePorId.getBody().getNombreNaveEspacial());
	}

	@Test
	void newSpaceShip() {
		NaveEspacial newNaveEspacialMock = new NaveEspacial();
		newNaveEspacialMock.setIdNaveEspacial(101L);
		newNaveEspacialMock.setNombreNaveEspacial("Nueva nave");
		Mockito.when(naveEspacialServiceMock.save(newNaveEspacialMock)).thenReturn(newNaveEspacialMock);

		ResponseEntity<NaveEspacial> returnedNaveEspacial = naveEspacialController.newSpaceShip(newNaveEspacialMock);

		Assertions.assertEquals(returnedNaveEspacial.getBody().getIdNaveEspacial(),
				newNaveEspacialMock.getIdNaveEspacial());

	}

	@Test
	void deleteSpaceShip() {
		NaveEspacial naveEspacial = new NaveEspacial();
		naveEspacial.setIdNaveEspacial(102L);
		naveEspacial.setNombreNaveEspacial("La nave a borrar");

		ResponseEntity<String> respuestaBorrado = naveEspacialController
				.deleteSpaceShip(naveEspacial.getIdNaveEspacial());

		Assertions.assertEquals(200, respuestaBorrado.getStatusCodeValue());

	}

}
