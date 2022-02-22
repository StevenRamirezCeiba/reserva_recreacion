package com.ceiba.usuario.comando;

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
	private Long valor;
	private LocalDateTime fechaReserva;
	private Long usuarioId;
	private Long reservaEstadoId;
}
