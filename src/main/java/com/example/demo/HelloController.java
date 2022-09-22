package com.example.demo;

import java.util.HashMap;

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
public class HelloController {
	
	HashMap<String, String> idiomas = new HashMap<String, String>();
	
	
	public HelloController() {
		idiomas.put("ES", "Hola, Mundo!");
		idiomas.put("EN", "Hello, World!");
		idiomas.put("IT", "Ciao, Mondo!");
		idiomas.put("FR", "Bonjour le monde!");
		idiomas.put("DE", "Hallo Welt!");
		idiomas.put("ZH", "你好世界!");
		idiomas.put("PL", "Witaj świecie!");
	}
	
	
	
	
	@RequestMapping(method=RequestMethod.GET, value="/saludar")
	public ResponseEntity<?> dameNombre(@RequestParam (defaultValue="ES") String idioma) {
		if(idiomas.containsKey(idioma.toUpperCase())) {
			return new ResponseEntity<>(idiomas.get(idioma), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Mal iidioma",HttpStatus.BAD_REQUEST);
		}	
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/crearIdioma")
	public ResponseEntity<?> creaIdioma(@RequestParam String idioma, @RequestParam String traduccion) {
		if(!idiomas.containsKey(idioma)) {
			idiomas.put(idioma, traduccion);
			return new ResponseEntity<>("Idioma "+idioma +" añadido con la traducción "+traduccion, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Ya existe",HttpStatus.BAD_REQUEST);
		}	
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/borrarIdioma")
	public ResponseEntity<?> borraIdioma(@RequestParam String idioma) {
		if(idiomas.containsKey(idioma)) {
			idiomas.remove(idioma);
			return new ResponseEntity<>("Idioma "+idioma +" eliminado.", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("No existe tal idioma, lo siento.",HttpStatus.BAD_REQUEST);
		}	
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/modificarIdioma")
	public ResponseEntity<?> modificaIdioma(@RequestParam String idioma, @RequestParam String traduccion) {
		if(idiomas.containsKey(idioma)) {
			idiomas.put(idiomas.get(idioma),traduccion);
			return new ResponseEntity<>("Idioma "+idioma +" modificado para que su traducción sea "+traduccion, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("No se puede modificar un idioma que no existe.",HttpStatus.BAD_REQUEST);
		}	
	}
	
	

}
