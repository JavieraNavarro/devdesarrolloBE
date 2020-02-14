package com.latam.desarrollo.be.desarrolloBE.services.test;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.latam.desarrollo.be.desarrolloBE.entities.Persona;
import com.latam.desarrollo.be.desarrolloBE.entities.Poema;
import com.latam.desarrollo.be.desarrolloBE.entities.Poeta;
import com.latam.desarrollo.be.desarrolloBE.entities.ResponsePersona;
import com.latam.desarrollo.be.desarrolloBE.services.PersonaService;
import com.latam.desarrollo.be.desarrolloBE.services.impl.PersonaServiceImpl;
import com.latam.desarrollo.be.desarrolloBE.util.FormatDate;

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class PersonaServiceImplTest {

	@InjectMocks
	PersonaServiceImpl personaService;

	@InjectMocks
	FormatDate formatDate;

	@Mock
	PersonaServiceImpl personaServiceMock;

	@InjectMocks
	FormatDate formatDateMock;

	@Mock
	RestTemplate restTemplate;
	private Persona persona = new Persona();
	private Persona persona1 = new Persona();
	private Persona persona2 = new Persona();
	private ResponsePersona resp = new ResponsePersona();
	Poema poem = new Poema();
	Poeta poeta = new Poeta();
	List<Poema> list = new ArrayList<>();
	List<Poema> list2 = new ArrayList<>();
	private ResponseEntity<List<Poema>> respPoema = new ResponseEntity<List<Poema>>(HttpStatus.OK);
	private String poema = "";
	private String ultimo = "";
	private String finaliza = "";
	private String ultimo2 = "";
	private String fecha = "";
	private String fecha1 = "";
	private int anio = 0;
	private int anio2 = 0;
	private String fechaError = "";

	@Before
	public void init() {

		persona.setNombreCompleto("Andres Acevedo Huenchullan");
		persona.setFechaNacimiento("2008-03-15");

		persona1.setNombreCompleto("Andres Acevedo Huenchullan");
		persona1.setFechaNacimiento("2008-02-13");
		poema = "poema";
		poem.setTitle("title");
		poem.setContent("poema");
		poeta.setName("name");
		poeta.setUrl("url");
		poem.setPoet(poeta);
		list.add(poem);

		respPoema = new ResponseEntity<List<Poema>>(list, HttpStatus.OK);
		resp.setFelicitaciones(list.get(0).getTitle() + list.get(0).getContent());

		persona2.setNombreCompleto("Andres Acevedo Huenchullan");
		persona2.setFechaNacimiento("2008-02-14");
		ultimo = "Huenchullan";
		ultimo2 = "A";
		finaliza = "Andres Acevedo";
		fecha = "2019-02-13";
		fecha1 = "2019-02-14";
		anio = 2019;
		anio2 = 2020;
		fechaError = "13/02/2019";
	}

	@Test
	public void testObtenerFelicitaciones() {

		formatDate.splitAnio(persona.getFechaNacimiento());
		personaService.obtenerFelicitaciones(persona);
	}

	@Test
	public void testObtenerFelicitaciones2() {
		formatDate.calcularEdad(persona.getFechaNacimiento());
		personaService.obtenerFelicitaciones(persona1);
	}


	@Test
	public void testCallServicePoem() {
		when(personaServiceMock.callServicePoem()).thenReturn(respPoema);
		personaService.callServicePoem();
	}

	@Test
	public void testEliminarUltimaPalabra() {
		when(personaServiceMock.eliminarUltimaPalabra(persona.getNombreCompleto())).thenReturn(ultimo);
		personaService.eliminarUltimaPalabra(persona.getNombreCompleto());
	}

	@Test
	public void testRemoveFromEnd() {
		when(personaServiceMock.removeFromEnd(persona.getNombreCompleto(), ultimo)).thenReturn(finaliza);
		personaService.removeFromEnd(persona.getNombreCompleto(), ultimo);
	}

	@Test
	public void testRemoveFromEnd2() {
		
		personaService.removeFromEnd(null, ultimo);
	}

	@Test
	public void testRemoveFromEnd3() {

		personaService.removeFromEnd(persona.getNombreCompleto(), ultimo2);
	}

	@Test
	public void splitAnio() {

		formatDate.splitAnio(fecha);

	}

	@Test
	public void testcambiarFormatoFecha() {
		formatDate.cambiarFormatoFecha(fecha);
	}

	@Test
	public void testcambiarFormatoFechaRequest() {
		formatDate.cambiarFormatoFechaRequest(fecha);
	}

	@Test
	public void testcalculateBirthDay() {

		formatDate.calculateBirthDay(fecha1);
	}

	@Test
	public void testcalculateBirthDayError() {
		formatDate.calculateBirthDay(fechaError);

	}

	@Test
	public void testcambiarFormatoFechaRequestError() {
		formatDate.cambiarFormatoFechaRequest(fechaError);
	}

	@Test
	public void testcambiarFormatoFechaError() {
		formatDate.cambiarFormatoFecha(fechaError);
	}
}
