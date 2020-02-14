package com.latam.desarrollo.be.desarrolloBE.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.latam.desarrollo.be.desarrolloBE.entities.Persona;
import com.latam.desarrollo.be.desarrolloBE.entities.Poema;
import com.latam.desarrollo.be.desarrolloBE.entities.ResponsePersona;

public interface PersonaService {
	public ResponsePersona obtenerFelicitaciones(Persona persona);
	public ResponseEntity<List<Poema>> callServicePoem();

}
