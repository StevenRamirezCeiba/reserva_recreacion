package com.ceiba.usuario.modelo.entidad;

import java.time.LocalDateTime;

import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.dominio.ValidadorArgumento.validarPositivo;
import static com.ceiba.dominio.ValidadorArgumento.validarMenor;

@Getter
public class Reserva {
	
	private static final String SE_DEBE_INGRESAR_EL_VALOR = "Se debe ingresar el valor de la reserva";
	private static final String EL_VALOR_DEBE_SER_UN_NUMERO_REAL_POSITIVO = "El valor debe ser un numero real positivo";
	private static final String SE_DEBE_INGRESAR_LA_FECHA_RESERVA = "Se debe ingresar la fecha de reserva";
	private static final String LA_FECHA_DE_RESERVA_DEBE_SER_MAYOR_A_LA_FECHA_ACTUAL = "La fecha de reserva debe ser mayor a la fecha actual";
	private static final String SE_DEBE_INGRESAR_USUARIO_ID = "Se debe ingresar el usuarioId";
	
	private Long id;
	private Double valor;
	private LocalDateTime fechaCreacion;
	private LocalDateTime fechaReserva;
	private Long usuarioId;
	
	public Reserva(Long id, Double valor, LocalDateTime fechaReserva, Long usuarioId) {
		validarObligatorio(valor, SE_DEBE_INGRESAR_EL_VALOR);
		validarPositivo(valor, EL_VALOR_DEBE_SER_UN_NUMERO_REAL_POSITIVO);
		validarObligatorio(fechaReserva, SE_DEBE_INGRESAR_LA_FECHA_RESERVA);
		validarMenor(LocalDateTime.now(), fechaReserva, LA_FECHA_DE_RESERVA_DEBE_SER_MAYOR_A_LA_FECHA_ACTUAL);
		validarObligatorio(usuarioId, SE_DEBE_INGRESAR_USUARIO_ID);
		
		this.id = id;
		this.valor = valor;
		this.fechaCreacion = LocalDateTime.now();
		this.fechaReserva = fechaReserva;
		this.usuarioId = usuarioId;
	}
	
	
}
