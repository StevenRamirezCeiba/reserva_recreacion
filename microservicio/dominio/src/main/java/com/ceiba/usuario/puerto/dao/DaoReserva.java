package com.ceiba.usuario.puerto.dao;

import java.util.List;

import com.ceiba.usuario.modelo.dto.DtoReserva;

public interface DaoReserva {

	/**
     * Permite listar reservas
     * @return las reservas
     */
    List<DtoReserva> listar();
}
