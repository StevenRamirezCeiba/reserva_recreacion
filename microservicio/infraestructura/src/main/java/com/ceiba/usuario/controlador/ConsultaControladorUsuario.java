package com.ceiba.usuario.controlador;

import java.util.List;

import com.ceiba.usuario.consulta.ManejadorListarUsuarios;
import org.springframework.web.bind.annotation.*;

import com.ceiba.usuario.modelo.dto.DtoUsuario;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuarios")
@Api(tags={"Controlador consulta usuario"})
@CrossOrigin(origins = { "http://localhost:4200" })
public class ConsultaControladorUsuario {

    private final ManejadorListarUsuarios manejadorListarUsuarios;

    public ConsultaControladorUsuario(ManejadorListarUsuarios manejadorListarUsuarios) {
        this.manejadorListarUsuarios = manejadorListarUsuarios;
    }

    @GetMapping
    @ApiOperation("Listar Usuarios")
    public List<DtoUsuario> listar() {
        return this.manejadorListarUsuarios.ejecutar();
    }

    @GetMapping(value = "/{numeroDocumento}")
    @ApiOperation("Encontrar un usuario por su numero de documento")
    public DtoUsuario encontrarPorNumeroDocumento(@PathVariable Long numeroDocumento) {
        return this.manejadorListarUsuarios.encontrarPorNumeroDocumento(numeroDocumento);
    }

}
