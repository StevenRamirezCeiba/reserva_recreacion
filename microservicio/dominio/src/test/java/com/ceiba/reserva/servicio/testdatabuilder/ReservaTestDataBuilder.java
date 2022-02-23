package com.ceiba.reserva.servicio.testdatabuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ceiba.reserva.modelo.entidad.Reserva;

public class ReservaTestDataBuilder {

	private Long id;
	private BigDecimal valor;
	private LocalDateTime fechaReserva;
	private Long usuarioId;
	private Long reservaEstadoId;
	
	public ReservaTestDataBuilder() {
		valor = new BigDecimal(50000);
		fechaReserva = LocalDateTime.now().plusDays(3);
		usuarioId = 1L;
		reservaEstadoId = 1L;
	}
	
	public ReservaTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}
	
	public ReservaTestDataBuilder conValor(BigDecimal valor) {
		this.valor = valor;
		return this;
	}
	
	public ReservaTestDataBuilder conFechaReserva(LocalDateTime fechaReserva) {
		this.fechaReserva = fechaReserva;
		return this;
	}
	
	public ReservaTestDataBuilder conUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
		return this;
	}
	
	public ReservaTestDataBuilder conReservaEstadoId(Long reservaEstadoId) {
		this.reservaEstadoId = reservaEstadoId;
		return this;
	}
	
	public Reserva build() {
		return new Reserva(id, valor, fechaReserva, usuarioId, reservaEstadoId);
	}
}
