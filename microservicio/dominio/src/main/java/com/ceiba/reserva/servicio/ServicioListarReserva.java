package com.ceiba.reserva.servicio;

import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.usuario.servicio.ServicioListarUsuario;

import java.util.List;

public class ServicioListarReserva {

    private final RepositorioReserva repositorioReserva;
    private final ServicioListarUsuario servicioListarUsuario;

    public ServicioListarReserva(RepositorioReserva repositorioReserva, ServicioListarUsuario servicioListarUsuario) {
        this.repositorioReserva = repositorioReserva;
        this.servicioListarUsuario = servicioListarUsuario;
    }

    public List<DtoReserva> listarReservasPorUsuarioNumeroDocumento(Long numeroDocumento) {
        servicioListarUsuario.validarExistenciaPrevia(numeroDocumento);
        return repositorioReserva.listarPorUsuarioNumeroDocumento(numeroDocumento);
    }

}
