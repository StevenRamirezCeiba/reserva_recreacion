package com.ceiba.usuario.modelo.entidad;

import lombok.Getter;
import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class ReservaEstado {

	private static final String SE_DEBE_INGRESAR_EL_NOMBRE = "Se debe ingresar el nombre";
	
	private Long id;
	private String nombre;
	
	public ReservaEstado(Long id, String nombre) {
		validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE);
		
		this.id = id;
		this.nombre = nombre;
	}
}
