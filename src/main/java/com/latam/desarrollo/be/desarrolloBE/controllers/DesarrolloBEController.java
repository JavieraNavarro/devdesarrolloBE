package com.latam.desarrollo.be.desarrolloBE.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.latam.desarrollo.be.desarrolloBE.entities.Persona;
import com.latam.desarrollo.be.desarrolloBE.entities.Poema;
import com.latam.desarrollo.be.desarrolloBE.entities.ResponsePersona;
import com.latam.desarrollo.be.desarrolloBE.services.PersonaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DesarrolloBEController {

	@Autowired
	private PersonaService personaService;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/personabe/felicitaciones", method = RequestMethod.POST)
	public ResponseEntity<ResponsePersona> obtenerFelicitaciones(@RequestBody Persona persona) {

		log.info("persona: " + persona);

		return new ResponseEntity<ResponsePersona>(personaService.obtenerFelicitaciones(persona), HttpStatus.OK);

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/personabe/felicitaciones1", method = RequestMethod.GET)
	public ResponseEntity<List<Poema>> callService() {
		ResponseEntity<List<Poema>> resp = new ResponseEntity<List<Poema>>(HttpStatus.NO_CONTENT);
		resp = personaService.callServicePoem();

		return resp;

	}
}
