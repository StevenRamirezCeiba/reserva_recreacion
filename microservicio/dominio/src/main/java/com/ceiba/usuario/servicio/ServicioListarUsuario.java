package com.ceiba.usuario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;

public class ServicioListarUsuario {

    private static final String EL_USUARIO_CON_ESTE_NUMERO_DE_DOCUMENTO_NO_EXISTE_EN_EL_SISTEMA = "El usuario con este número de documento no existe en el sistema";

    private final RepositorioUsuario repositorioUsuario;

    public ServicioListarUsuario(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public DtoUsuario encontrarPorNumeroDocumento(Long numeroDocumento) {
        validarExistenciaPrevia(numeroDocumento);
        return repositorioUsuario.encontrarPorNumeroDocumento(numeroDocumento);
    }

    public void validarExistenciaPrevia(Long numeroDocumento) {
        boolean existe = this.repositorioUsuario.existePorNumeroDocumento(numeroDocumento);
        if(!existe) {
            throw new ExcepcionSinDatos(EL_USUARIO_CON_ESTE_NUMERO_DE_DOCUMENTO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
