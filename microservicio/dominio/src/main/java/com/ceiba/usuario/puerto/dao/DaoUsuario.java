package com.ceiba.usuario.puerto.dao;

import com.ceiba.usuario.modelo.dto.DtoUsuario;

import java.util.List;

public interface DaoUsuario {

    /**
     * Permite listar usuarios
     * @return los usuarios
     */
    List<DtoUsuario> listar();

    /**
     * Encuenta un usuario por su numero de documento
     * @param numeroDocumento
     * @return
     */
    DtoUsuario encontrarPorNumeroDocumento(Long numeroDocumento);
}
