package com.kamila.backofficeteste.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kamila.backofficeteste.domain.Tecnico;
import com.kamila.backofficeteste.domain.dtos.TecnicoDTO;
import com.kamila.backofficeteste.services.TecnicoService;

//isso aqui vai receber a camada de requisição HTPP e services
@RestController
//adiciona o endpoint inicial dos serviços.no caso, o endpoint de tecnico 
@RequestMapping(value = "/tecnicos") // ou seja a requisição vai ler o que vai ser trazido da api ex: localhost:8080/tecnicos
public class TecnicoResourse {
  
	@Autowired
	private TecnicoService service;
	
	//isso aqui significa que podemos controlar qualque coisa como o cabeçalho ou o corpo da requisição e é uma boa praticar usar
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
		Tecnico obj= service.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {
		List<Tecnico> list = service.findAll();
		List<TecnicoDTO> listDTO = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO) {
		Tecnico newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDTO) {
		Tecnico obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id) {
		service.delete(id); 
		return ResponseEntity.noContent().build();
	}
}
