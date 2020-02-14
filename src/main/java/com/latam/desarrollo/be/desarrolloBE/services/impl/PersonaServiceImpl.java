package com.latam.desarrollo.be.desarrolloBE.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.latam.desarrollo.be.desarrolloBE.entities.Persona;
import com.latam.desarrollo.be.desarrolloBE.entities.Poema;
import com.latam.desarrollo.be.desarrolloBE.entities.ResponsePersona;
import com.latam.desarrollo.be.desarrolloBE.services.PersonaService;
import com.latam.desarrollo.be.desarrolloBE.util.FormatDate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonaServiceImpl implements PersonaService {
	private RestTemplate restTemplate = new RestTemplate();
	private FormatDate formatDate = new FormatDate();

	@Override
	public ResponsePersona obtenerFelicitaciones(Persona persona) {
		List<ResponsePersona> personas = new ArrayList<>();
		ResponsePersona rpersona = new ResponsePersona();
		// String fechaNac =
		// formatDate.cambiarFormatoFechaRequest(persona.getFechaCumple());
		long totalDias = formatDate.calculateBirthDay(persona.getFechaNacimiento());
		int edad = 0;
		if (totalDias == 0) {
			ResponseEntity<List<Poema>> resp = new ResponseEntity<List<Poema>>(HttpStatus.OK);
			resp = callServicePoem();
			String poema = resp.getBody().get(0).getTitle() + "\t" + resp.getBody().get(0).getContent();
			rpersona.setFelicitaciones(poema);
			rpersona.setFecha(formatDate.cambiarFormatoFecha(persona.getFechaNacimiento()));
			edad = formatDate.calcularEdad(persona.getFechaNacimiento());
			rpersona.setEdad(edad);
			String nombre = persona.getNombreCompleto();
			String apellido = eliminarUltimaPalabra(nombre);
			String finaliza = removeFromEnd(nombre, apellido);
			rpersona = obtenerNombreApellido(finaliza, rpersona);

		} else if (totalDias < 0) {
			long calculo = 365 + totalDias;

			rpersona.setDias(calculo);
			edad = formatDate.calcularEdad(persona.getFechaNacimiento());
			rpersona.setEdad(edad);
			String nombre = persona.getNombreCompleto();
			String apellido = eliminarUltimaPalabra(nombre);
			String finaliza = removeFromEnd(nombre, apellido);
			rpersona = obtenerNombreApellido(finaliza, rpersona);
			rpersona.setFecha(formatDate.cambiarFormatoFecha(persona.getFechaNacimiento()));

		} else {
			long calculo;
			int anio = formatDate.splitAnio(persona.getFechaNacimiento());

			calculo = totalDias;
			rpersona.setDias(calculo);
			rpersona.setEdad(formatDate.calcularEdad(persona.getFechaNacimiento()));
			String nombre = persona.getNombreCompleto();
			String apellido = eliminarUltimaPalabra(nombre);
			String finaliza = removeFromEnd(nombre, apellido);
			rpersona = obtenerNombreApellido(finaliza, rpersona);
			rpersona.setFecha(formatDate.cambiarFormatoFecha(persona.getFechaNacimiento()));
		}

		return rpersona;
	}

	public ResponseEntity<List<Poema>> callServicePoem() {
		List<Poema> list = new ArrayList<>();
		ResponseEntity<List<Poema>> respMensaje = new ResponseEntity<List<Poema>>(list, HttpStatus.OK);
		respMensaje = restTemplate.exchange("https://www.poemist.com/api/v1/randompoems", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Poema>>() {
				});
		return respMensaje;
	}

	public ResponsePersona obtenerNombreApellido(String nombre, ResponsePersona rpersona) {
		// String nombreSplit = nombre.replaceAll("\\s+", " ");
		String[] nombres = nombre.split(" ");
		for (String n : nombres) {
			rpersona.setNombre(nombres[0]);
			rpersona.setApellido(nombres[nombres.length - 1]);
		}
		return rpersona;

	}

	public String eliminarUltimaPalabra(String str) {
		String strTrimmed = str.trim();
		String finalString = strTrimmed.substring(strTrimmed.lastIndexOf(" ", strTrimmed.length()));
		return finalString;
	}

	public String removeFromEnd(String in, String rem) {
		if (in != null) {
			if (in.endsWith(rem)) {
				return in.substring(0, in.length() - rem.length()).trim();
			}
			return in.trim();
		}
		return null;
	}

}
