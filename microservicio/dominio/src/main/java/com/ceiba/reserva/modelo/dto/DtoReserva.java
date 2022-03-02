package com.ceiba.reserva.modelo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoReserva {

	private Long id;
	private BigDecimal valor;
	private LocalDateTime fechaCreacion;
	private LocalDateTime fechaReserva;
	private Long usuarioId;
	private Long reservaEstadoId;
	private String reservaEstadoNombre;
}
