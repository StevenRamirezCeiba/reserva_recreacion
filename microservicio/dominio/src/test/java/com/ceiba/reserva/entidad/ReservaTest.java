package com.ceiba.reserva.entidad;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;

public class ReservaTest {

	@Test
	@DisplayName("Deberia crear correctamente la reserva")
	void deberiaCrearCorrectamenteLaReserva() {
		// arrange
		LocalDateTime fechaReserva = LocalDateTime.now().plusDays(3);
		// act
		Reserva reserva = new ReservaTestDataBuilder().conFechaReserva(fechaReserva).conId(1L).build();
		// assert
		assertEquals(1, reserva.getId());
		assertEquals(new BigDecimal(50000), reserva.getValor());
		assertEquals(fechaReserva, reserva.getFechaReserva());
		assertEquals(1, reserva.getUsuarioId());
	}

	@Test
	void deberiaFallarSinFechaCreacion() {
		// arrange
		ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaCreacion(null).conId(1L);
		// act-assert
		BasePrueba.assertThrows(() -> {
			reservaTestDataBuilder.build();
		}, ExcepcionValorObligatorio.class, "Se debe ingresar la fecha de creación");
	}

	@Test
	void deberiaFallarSinFechaReserva() {
		// arrange
		ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaReserva(null).conId(1L);
		// act-assert
		BasePrueba.assertThrows(() -> {
			reservaTestDataBuilder.build();
		}, ExcepcionValorObligatorio.class, "Se debe ingresar la fecha de reserva");
	}

	@Test
	void deberiaFallarSiFechaReservaEsMenorAFechaActual() {
		// arrange
		LocalDateTime fechaReserva = LocalDateTime.now().minusHours(3);
		ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaReserva(fechaReserva)
				.conId(1L);
		// act-assert
		BasePrueba.assertThrows(() -> {
			reservaTestDataBuilder.build();
		}, ExcepcionValorInvalido.class, "La fecha de reserva debe ser mayor a la fecha actual");
	}

	@Test
	void deberiaFallarSinUsuarioId() {
		// arrange
		ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conUsuarioId(null).conId(1L);
		// act-assert
		BasePrueba.assertThrows(() -> {
			reservaTestDataBuilder.build();
		}, ExcepcionValorObligatorio.class, "Se debe ingresar el usuarioId");
	}

	@Test
	void deberiaFallarSinReservaEstadoId() {
		// arrange
		ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conReservaEstadoId(null).conId(1L);
		// act-assert
		BasePrueba.assertThrows(() -> {
			reservaTestDataBuilder.build();
		}, ExcepcionValorObligatorio.class, "Se debe ingresar el reservaEstadoId");
	}

	@Test
	void deberiaAplicarDescuentoAlValor() {
		// arrange
		Reserva reserva = new ReservaTestDataBuilder().conId(1L).build();
		// act
		reserva.realizarDescuento(new BigDecimal(30));
		// assert
		assertEquals(new BigDecimal("35000.0"), reserva.getValor());
	}

}
