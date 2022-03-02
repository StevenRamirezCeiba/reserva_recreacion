package com.ceiba.usuario.consulta;

import java.util.List;

import com.ceiba.usuario.puerto.dao.DaoUsuario;
import com.ceiba.usuario.servicio.ServicioListarUsuario;
import org.springframework.stereotype.Component;

import com.ceiba.usuario.modelo.dto.DtoUsuario;

@Component
public class ManejadorListarUsuarios {

    private final DaoUsuario daoUsuario;
    private final ServicioListarUsuario servicioListarUsuario;

    public ManejadorListarUsuarios(DaoUsuario daoUsuario, ServicioListarUsuario servicioListarUsuario){
        this.daoUsuario = daoUsuario;
        this.servicioListarUsuario = servicioListarUsuario;
    }

    public List<DtoUsuario> ejecutar(){ return this.daoUsuario.listar(); }

    public DtoUsuario encontrarPorNumeroDocumento(Long numeroDocumento){return this.servicioListarUsuario.encontrarPorNumeroDocumento(numeroDocumento);}
}
