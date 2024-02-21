package com.world2meettest.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.world2meettest.app.exception.SpaceShipNotFoundException;
import com.world2meettest.app.models.entity.NaveEspacial;
import com.world2meettest.app.models.service.Interface.INaveEspacialService;
import com.world2meettest.app.utils.AppConstants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Naves Espaciales", description = "API de naves espaciales")
@RestController
@RequestMapping("/api/v1")
public class NaveEspacialController {

	@Autowired
	INaveEspacialService naveEspacialService;

	public NaveEspacialController(INaveEspacialService naveEspacialService) {
		this.naveEspacialService = naveEspacialService;
	}

	@Operation(summary = "Obtener todas las naves espaciales", description = "obtiene todas las naves espaciales por paginación")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	@GetMapping(value = "/navesespaciales")
	public Page<NaveEspacial> getAllSpaceShips(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "id_nave_espacial", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		return naveEspacialService.findAllPageable(pageable);

	}

	@Operation(summary = "Obtener una nave", description = "obtener una nave por id de nave")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	@GetMapping("/navesespaciales/{idNaveEspacial}")
	public ResponseEntity<NaveEspacial> getSpaceShipById(@PathVariable Long idNaveEspacial) {
		return new ResponseEntity<>(naveEspacialService.findNaveEspacialById(idNaveEspacial), HttpStatus.OK);
	}

	@Operation(summary = "Obtener listado de naves", description = "obtener listado de naves que contengan en su nombre el parámetro solicitado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	@GetMapping("/navesespacialesN/{name}")
	public ResponseEntity<List<NaveEspacial>> getSpaceShipByName(@PathVariable String name) {

		List<NaveEspacial> naveEspacialList = naveEspacialService.findNaveEspacialByName(name.toUpperCase());
		if (!naveEspacialList.isEmpty()) {
			return new ResponseEntity<>(naveEspacialService.findNaveEspacialByName(name.toUpperCase()), HttpStatus.OK);
		} else {
			throw new SpaceShipNotFoundException("No se ha encontrado ninguna nave espacial que contenga ese nombre.");
		}

	}

	@Operation(summary = "Crear nave", description = "crear una nave espacial")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "successful operation") })
	@PostMapping("/navesespaciales")
	public ResponseEntity<NaveEspacial> newSpaceShip(@Valid @RequestBody NaveEspacial naveEspacial) {

		return new ResponseEntity<>(naveEspacialService.save(naveEspacial), HttpStatus.CREATED);

	}

	@Operation(summary = "Modificar una nave", description = "Modificar el nombre de una nave por id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	@PutMapping("/navesespaciales")
	public ResponseEntity<NaveEspacial> modifySpaceShip(@RequestBody NaveEspacial naveEspacial) {

		naveEspacialService.findNaveEspacialById(naveEspacial.getIdNaveEspacial());
		naveEspacialService.save(naveEspacial);

		return new ResponseEntity<>(naveEspacialService.save(naveEspacial), HttpStatus.OK);
	}

	@Operation(summary = "Eliminar nave", description = "Eliminar una nave por id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	@DeleteMapping("/navesespaciales/{idNaveEspacial}")
	public ResponseEntity<String> deleteSpaceShip(@PathVariable(name = "idNaveEspacial") Long idNaveEspacial) {

		naveEspacialService.deleteNaveEspacialById(idNaveEspacial);

		return new ResponseEntity<>("Nave borrada correctamente", HttpStatus.OK);
	}

}