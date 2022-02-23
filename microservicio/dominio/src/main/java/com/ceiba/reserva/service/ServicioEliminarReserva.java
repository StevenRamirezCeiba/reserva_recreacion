package com.ceiba.reserva.service;

import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

public class ServicioEliminarReserva {

    private final RepositorioReserva repositorioReserva;

    public ServicioEliminarReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    public void ejecutar(Long id) {
        this.repositorioReserva.eliminar(id);
    }
}
