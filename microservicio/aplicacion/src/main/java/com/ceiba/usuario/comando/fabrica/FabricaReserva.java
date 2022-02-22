package com.ceiba.usuario.comando.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.usuario.comando.ComandoReserva;
import com.ceiba.usuario.modelo.entidad.Reserva;

@Component
public class FabricaReserva {

	public Reserva crear(ComandoReserva comandoReserva) {
        return new Reserva(
        		comandoReserva.getId(),
        		comandoReserva.getValor(),
        		comandoReserva.getFechaReserva(),
        		comandoReserva.getUsuarioId(),
        		comandoReserva.getReservaEstadoId());
    }
}
