package com.ceiba.reserva.puerto.dao;

import java.util.List;

import com.ceiba.reserva.modelo.dto.DtoReserva;

public interface DaoReserva {

	/**
     * Permite listar reservas
     * @return las reservas
     */
    List<DtoReserva> listar();
}
