package com.ceiba.usuario.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioTest {

	@Test
	@DisplayName("Deberia crear correctamente el usuario")
	void deberiaCrearCorrectamenteElUsuario() {
		// arrange
		Usuario usuario = new UsuarioTestDataBuilder().conId(1L).build();
		// act-assert
		assertEquals(1, usuario.getId());
		assertEquals("CHRISTIAN", usuario.getNombre());
		assertEquals("RAMIREZ", usuario.getApellido());
		assertEquals("1234", usuario.getClave());
		assertEquals(1075318997, usuario.getNumeroDocumento());
		assertEquals(0, usuario.getReservasAcumulado());
		assertEquals(1, usuario.getCategoriaId());
	}

	@Test
	void deberiaFallarSinNombre() {

		// Arrange
		UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conNombre(null).conId(1L);
		// act-assert
		BasePrueba.assertThrows(() -> {
			usuarioTestDataBuilder.build();
		}, ExcepcionValorObligatorio.class, "Se debe ingresar el nombre");
	}

	@Test
	void deberiaFallarSinApellido() {
		// Arrange
		UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conApellido(null).conId(1L);
		// act-assert
		BasePrueba.assertThrows(() -> {
			usuarioTestDataBuilder.build();
		}, ExcepcionValorObligatorio.class, "Se debe ingresar el apellido");
	}

	@Test
	void deberiaFallarSinClave() {

		// Arrange
		UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conClave(null).conId(1L);
		// act-assert
		BasePrueba.assertThrows(() -> {
			usuarioTestDataBuilder.build();
		}, ExcepcionValorObligatorio.class, "Se debe ingresar la clave");
	}

	@Test
	void deberiaFallarSinTamanioClave() {

		// Arrange
		UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conClave("123").conId(1L);
		// act-assert
		BasePrueba.assertThrows(() -> {
			usuarioTestDataBuilder.build();
		}, ExcepcionLongitudValor.class, "La clave debe tener una longitud mayor o igual a 4");
	}
	
	@Test
	void deberiaFallarSinNumeroDocumento() {

		// Arrange
		UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conNumeroDocumento(null).conId(1L);
		// act-assert
		BasePrueba.assertThrows(() -> {
			usuarioTestDataBuilder.build();
		}, ExcepcionValorObligatorio.class, "Se debe ingresar el numero de documento");
	}

	@Test
	void deberiaFallarSinTamanioNumeroDocumento() {

		// Arrange
		UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conNumeroDocumento(12345678910L).conId(1L);
		// act-assert
		BasePrueba.assertThrows(() -> {
			usuarioTestDataBuilder.build();
		}, ExcepcionLongitudValor.class, "El numero de documento debe tener una longitud menor o igual a 10");
	}
	
	@Test
	void deberiaFallarSinReservasAcumulado() {

		// Arrange
		UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conReservasAcumulado(null).conId(1L);
		// act-assert
		BasePrueba.assertThrows(() -> {
			usuarioTestDataBuilder.build();
		}, ExcepcionValorObligatorio.class, "Se debe ingresar el acumulado de reservas");
	}
	
	@Test
	void deberiaFallarSinTamanioReservasAcumulado() {

		// Arrange
		UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conReservasAcumulado(10).conId(1L);
		// act-assert
		BasePrueba.assertThrows(() -> {
			usuarioTestDataBuilder.build();
		}, ExcepcionLongitudValor.class, "El acumulado de reservas debe tener una longitud menor o igual a 1");
	}

	@Test
	void deberiaFallarSinCategoriaId() {

		// Arrange
		UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conCategoriaId(null).conId(1L);
		// act-assert
		BasePrueba.assertThrows(() -> {
			usuarioTestDataBuilder.build();
		}, ExcepcionValorObligatorio.class, "Se debe ingresar la categoria del usuario");
	}
	
}
