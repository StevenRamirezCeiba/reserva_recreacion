package com.ceiba.usuario.puerto.repositorio;

import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.usuario.modelo.entidad.Usuario;

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
     * Permite eliminar un usuario
     * @param id
     */
    void eliminar(Long id);

    /**
     * Permite validar si existe un usuario con un nombre
     * @param nombre
     * @return si existe o no
     */
    boolean existe(String nombre);

    /**
     * Permite validar si existe un usuario con un nombre excluyendo un id
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

}
