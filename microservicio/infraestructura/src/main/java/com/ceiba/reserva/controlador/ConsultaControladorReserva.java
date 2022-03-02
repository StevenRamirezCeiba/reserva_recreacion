package com.ceiba.reserva.controlador;

import com.ceiba.reserva.consulta.ManejadorListarReservas;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@Api(tags = { "Controlador consulta reserva" })
@CrossOrigin(origins = { "http://localhost:4200" })
public class ConsultaControladorReserva {

	private final ManejadorListarReservas manejadorListarReservas;

	public ConsultaControladorReserva(ManejadorListarReservas manejadorListarReservas) {
		this.manejadorListarReservas = manejadorListarReservas;
	}

	@GetMapping
	@ApiOperation("Listar Reservas")
	public List<DtoReserva> listar() {
		return this.manejadorListarReservas.ejecutar();
	}

}