package com.ceiba.usuario.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.usuario.comando.ComandoUsuario;
import com.ceiba.usuario.comando.manejador.ManejadorActualizarUsuario;
import com.ceiba.usuario.comando.manejador.ManejadorCrearUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuarios")
@Api(tags = { "Controlador comando usuario"})
@CrossOrigin(origins = { "http://localhost:4200" })
public class ComandoControladorUsuario {

    private final ManejadorCrearUsuario manejadorCrearUsuario;
	private final ManejadorActualizarUsuario manejadorActualizarUsuario;

    @Autowired
    public ComandoControladorUsuario(ManejadorCrearUsuario manejadorCrearUsuario,
									 ManejadorActualizarUsuario manejadorActualizarUsuario) {
        this.manejadorCrearUsuario = manejadorCrearUsuario;
		this.manejadorActualizarUsuario = manejadorActualizarUsuario;
    }

    @PostMapping
    @ApiOperation("Crear Usuario")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoUsuario comandoUsuario) {
        return manejadorCrearUsuario.ejecutar(comandoUsuario);
    }

	@PutMapping(value="/{id}")
	@ApiOperation("Actualizar Usuario")
	public void actualizar(@RequestBody ComandoUsuario comandoUsuario,@PathVariable Long id) {
		comandoUsuario.setId(id);
		manejadorActualizarUsuario.ejecutar(comandoUsuario);
	}
}
