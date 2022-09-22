package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@ResponseBody
@Transactional//EH? Por qué hace falta esto aquí?
public class HelloController {
	
	HashMap<String, String> idiomas = new HashMap<String, String>();
	
	@Autowired
	RepositorioIdiomas repoIdiomas;
	
	
	@RequestMapping(method=RequestMethod.GET, value="/saludar")
	public ResponseEntity<Idioma> dameNombre(@RequestParam (defaultValue="ES") String idioma) {
		List<Idioma> idiomaElegido = repoIdiomas.findByNombreIdioma(idioma);
		if(idiomaElegido.size()!=0) {
			return new ResponseEntity<>(idiomaElegido.get(0), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST, value="/crearIdioma")
	public ResponseEntity<Idioma> creaIdioma(@RequestParam String idioma, @RequestParam String traduccion) {
		try {
		Idioma _idioma = repoIdiomas
				.save(new Idioma(idioma, traduccion));
		return new ResponseEntity<>(_idioma, HttpStatus.CREATED);
	} catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
	}
	

	
	@RequestMapping(method=RequestMethod.DELETE, value="/borrarIdioma")
	public ResponseEntity<?> borraIdioma(@RequestParam String idioma) {
		List<Idioma> idiomaElegido = repoIdiomas.findByNombreIdioma(idioma);
		if(idiomaElegido.size()!=0) {
			repoIdiomas.deleteByNombreIdioma(idioma);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/modificarIdioma")	
	public ResponseEntity<?> modificaIdioma(@RequestParam String idioma, @RequestParam String traduccion) {
		List<Idioma> idiomaElegido = repoIdiomas.findByNombreIdioma(idioma);
		if(idiomaElegido.size()!=0) {
			Idioma aCambiar=idiomaElegido.get(0);
			aCambiar.setTraduccionIdioma(traduccion);
			return new ResponseEntity<>(repoIdiomas.save(aCambiar), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	

}
