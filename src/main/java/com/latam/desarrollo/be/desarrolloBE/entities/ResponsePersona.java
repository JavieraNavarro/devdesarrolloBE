package com.latam.desarrollo.be.desarrolloBE.entities;

import lombok.Data;

@Data
public class ResponsePersona {
	private String nombre;
	private String apellido;
	private String fecha;
	private int edad;
	private String felicitaciones;
	private long dias;

}
