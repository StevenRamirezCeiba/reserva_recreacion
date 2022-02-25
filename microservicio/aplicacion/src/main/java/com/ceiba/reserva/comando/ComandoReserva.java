package com.ceiba.reserva.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoReserva {

	private Long id;
	private BigDecimal valor;
	private LocalDateTime fechaCreacion;
	private LocalDateTime fechaReserva;
	private Long usuarioId;
	private Long reservaEstadoId;
}
