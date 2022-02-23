package com.ceiba.usuario.puerto.repositorio;

import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.usuario.modelo.entidad.Usuario;

import java.math.BigDecimal;

public interface RepositorioUsuario {
    /**
     * Permite crear un usuario
     * @param usuario
     * @return el id generado
     */
    Long crear(Usuario usuario);

    /**
     * Permite actualizar un usuario
     * @param usuario
     */
    void actualizar(Usuario usuario);

    /**
     * Permite validar si existe un usuario con un numero de documento
     * @param numeroDocumento
     * @return si existe o no
     */
    boolean existePorNumeroDocumento(Long numeroDocumento);

    /**
     * Permite validar si existe un usuario por su id
     * @return si existe o no
     */
    boolean existePorId(Long id);
    
    /**
     * Permite encontrar un usuario por id
     * @return usuario encontrado
     * @param id
     */
    DtoUsuario encontrarPorId(Long id);
    
    /**
     * Actualiza el valor de reservasAcumulado
     * @param id
     */
    void actualizarReservasAcumulado(Long id, Integer reservasAcumulado);

    BigDecimal obtenerTarifaPorUsuarioId(Long id);

}
