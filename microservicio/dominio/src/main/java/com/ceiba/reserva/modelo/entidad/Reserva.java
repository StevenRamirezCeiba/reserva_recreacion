package com.ceiba.reserva.modelo.entidad;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.dominio.ValidadorArgumento.validarMenor;

@Getter
public class Reserva {


	private static final String SE_DEBE_INGRESAR_EL_VALOR = "Se debe ingresar el valor de la reserva";
	private static final String SE_DEBE_INGRESAR_LA_FECHA_CREACION = "Se debe ingresar la fecha de creación";
	private static final String SE_DEBE_INGRESAR_LA_FECHA_RESERVA = "Se debe ingresar la fecha de reserva";
	private static final String LA_FECHA_DE_RESERVA_DEBE_SER_MAYOR_A_LA_FECHA_ACTUAL = "La fecha de reserva debe ser mayor a la fecha actual";
	private static final String SE_DEBE_INGRESAR_USUARIO_ID = "Se debe ingresar el usuarioId";
	private static final String SE_DEBE_INGRESAR_RESERVA_ESTADO_ID = "Se debe ingresar el reservaEstadoId";
	
	private Long id;
	@Setter private BigDecimal valor;
	private LocalDateTime fechaCreacion;
	private LocalDateTime fechaReserva;
	private Long usuarioId;
	private Long reservaEstadoId;
	
	public Reserva(Long id, BigDecimal valor, LocalDateTime fechaCreacion, LocalDateTime fechaReserva, Long usuarioId, Long reservaEstadoId) {
		validarObligatorio(fechaCreacion, SE_DEBE_INGRESAR_LA_FECHA_CREACION);
		validarObligatorio(fechaReserva, SE_DEBE_INGRESAR_LA_FECHA_RESERVA);
		validarMenor(fechaCreacion, fechaReserva, LA_FECHA_DE_RESERVA_DEBE_SER_MAYOR_A_LA_FECHA_ACTUAL);
		validarObligatorio(usuarioId, SE_DEBE_INGRESAR_USUARIO_ID);
		validarObligatorio(reservaEstadoId, SE_DEBE_INGRESAR_RESERVA_ESTADO_ID);
		
		this.id = id;
		this.valor = valor;
		this.fechaCreacion = fechaCreacion;
		this.fechaReserva = fechaReserva;
		this.usuarioId = usuarioId;
		this.reservaEstadoId = reservaEstadoId;
	}
	
	public void realizarDescuento(BigDecimal valorDescuento) {
        BigDecimal porcentajeDescuento = valorDescuento.divide(new BigDecimal(100));
        BigDecimal descuento = valor.multiply(porcentajeDescuento);
	    valor = valor.subtract(descuento);
	}
	
	
}
