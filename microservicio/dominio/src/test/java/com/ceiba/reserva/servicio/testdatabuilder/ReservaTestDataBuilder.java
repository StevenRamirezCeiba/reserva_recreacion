package com.ceiba.reserva.servicio.testdatabuilder;

import java.time.LocalDateTime;

import com.ceiba.usuario.modelo.entidad.Reserva;

public class ReservaTestDataBuilder {

	private Long id;
	private Double valor;
	private LocalDateTime fechaReserva;
	private Long usuarioId;
	
	public ReservaTestDataBuilder() {
		valor = 50000D;
		fechaReserva = LocalDateTime.now().plusDays(3);
		usuarioId = 1L;
	}
	
	public ReservaTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}
	
	public ReservaTestDataBuilder conValor(Double valor) {
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
	
	public Reserva build() {
		return new Reserva(id, valor, fechaReserva, usuarioId);
	}
}
