package com.ceiba.usuario.comando;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoReserva {

	private Long id;
	private BigDecimal valor;
	private LocalDateTime fechaReserva;
	private Long usuarioId;
	private Long reservaEstadoId;
}
