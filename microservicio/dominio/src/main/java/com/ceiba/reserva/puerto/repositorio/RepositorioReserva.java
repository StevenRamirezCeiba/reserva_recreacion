package com.ceiba.reserva.puerto.repositorio;

import java.time.LocalDate;
import java.util.List;

import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;

public interface RepositorioReserva {
	/**
     * Permite crear una reserva
     * @param reserva
     * @return el id generado
     */
    Long crear(Reserva reserva);

    /**
     * Permite actualizar una reserva
     * @param reserva
     */
    void actualizar(Reserva reserva);

    /**
     * Permite validar si existe una reserva por id
     * @param id
     */
    boolean existePorId(Long id);

    /**
     * Permite validar si existe una reserva en un dia en especifico
     * @return si existe o no
     */
    boolean existePorFechaReservaYUsuarioId(LocalDate fechaReserva, Long usuarioId);

    /**
     * Permite listar las reservas de acuerdo a el numero de documento del usuario
     * @param numeroDocumento
     * @return
     */
    List<DtoReserva> listarPorUsuarioNumeroDocumento(Long numeroDocumento);
}
