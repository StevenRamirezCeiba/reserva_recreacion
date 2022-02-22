package com.ceiba.usuario.puerto.repositorio;

import java.time.LocalDate;

import com.ceiba.usuario.modelo.entidad.Reserva;

public interface RepositorioReserva {
	/**
     * Permite crear una reserva
     * @param reserva
     * @return el id generado
     */
    Long crear(Reserva reserva);

    /**
     * Permite eliminar una reserva
     * @param id
     */
    void eliminar(Long id);

    /**
     * Permite validar si existe una reserva en un dia en especifico
     * @return si existe o no
     */
    boolean existePorFechaReservaYUsuarioId(LocalDate fechaReserva, Long usuarioId);
}
