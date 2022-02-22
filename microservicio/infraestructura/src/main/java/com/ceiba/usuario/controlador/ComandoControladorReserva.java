package com.ceiba.usuario.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.ComandoRespuesta;
import com.ceiba.usuario.comando.ComandoReserva;
import com.ceiba.usuario.comando.manejador.ManejadorCrearReserva;
import com.ceiba.usuario.comando.manejador.ManejadorEliminarReserva;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/reservas")
@Api(tags = { "Controlador comando reserva" })
public class ComandoControladorReserva {

	private final ManejadorCrearReserva manejadorCrearReserva;
	private final ManejadorEliminarReserva manejadorEliminarReserva;

	@Autowired
	public ComandoControladorReserva(ManejadorCrearReserva manejadorCrearReserva,
			ManejadorEliminarReserva manejadorEliminarReserva) {
		this.manejadorCrearReserva = manejadorCrearReserva;
		this.manejadorEliminarReserva = manejadorEliminarReserva;
	}
	
	@PostMapping
    @ApiOperation("Crear Reserva")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoReserva comandoReserva) {
        return manejadorCrearReserva.ejecutar(comandoReserva);
    }
	
	@DeleteMapping(value="/{id}")
	@ApiOperation("Eliminar Reserva")
	public void eliminar(@PathVariable Long id) {
		manejadorEliminarReserva.ejecutar(id);
	}
}
