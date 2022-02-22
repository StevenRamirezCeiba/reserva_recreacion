package com.ceiba.usuario.modelo.entidad;

import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.dominio.ValidadorArgumento.validarPositivo;

import java.math.BigDecimal;

@Getter
public class Categoria {
	
	private static final String SE_DEBE_INGRESAR_EL_NOMBRE = "Se debe ingresar el nombre";
	private static final String SE_DEBE_INGRESAR_LA_TARIFA = "Se debe ingresar la tarifa";
	private static final String LA_TARIFA_DEBE_SER_UN_NUMERO_REAL_POSITIVO = "El valor debe ser un numero real positivo";

	private Long id;
	private String nombre;
	private BigDecimal tarifa;
	
	public Categoria(Long id, String nombre, BigDecimal tarifa) {
		validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE);
		validarObligatorio(tarifa, SE_DEBE_INGRESAR_LA_TARIFA);
		validarPositivo(tarifa.doubleValue() , LA_TARIFA_DEBE_SER_UN_NUMERO_REAL_POSITIVO);
		
		this.id = id;
		this.nombre = nombre;
		this.tarifa = tarifa;
	}
}
