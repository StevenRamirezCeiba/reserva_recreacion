package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearReservaTest {

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando se valide la existencia usuario por id")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaUsuarioPorId() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        Mockito.when(repositorioUsuario.existePorId(Mockito.anyLong())).thenReturn(false);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva, repositorioUsuario);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ExcepcionSinDatos.class,"No se encontró el usuario en el sistema");
    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia por fechaReserva y usuarioId")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaPorfechaReservaYUsuarioId() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        Mockito.when(repositorioUsuario.existePorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioReserva.existePorFechaReservaYUsuarioId(Mockito.any(), Mockito.anyLong())).thenReturn(true);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva, repositorioUsuario);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ExcepcionDuplicidad.class,"Ya existe una reserva creada para la fecha seleccionada");
    }

    @Test
    @DisplayName("Deberia asignar el valor de la tarifa por usuario")
    void deberiaAsignarElValorDeLaTarifaPorUsuario() {
        //arrange
        LocalDateTime fechaReserva = LocalDateTime.now().plusDays(3).withHour(12);
        Reserva reserva = new ReservaTestDataBuilder().conId(1L).conFechaReserva(fechaReserva).build();
        DtoUsuario dtoUsuario = new UsuarioTestDataBuilder().conId(1L).buildDto();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        Mockito.when(repositorioUsuario.existePorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioReserva.existePorFechaReservaYUsuarioId(Mockito.any(), Mockito.anyLong())).thenReturn(false);
        Mockito.when(repositorioUsuario.obtenerTarifaPorUsuarioId(Mockito.anyLong())).thenReturn(new BigDecimal(50000));
        Mockito.when(repositorioUsuario.encontrarPorId(Mockito.anyLong())).thenReturn(dtoUsuario);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva, repositorioUsuario);
        // act
        servicioCrearReserva.ejecutar(reserva);
        // assert
        assertEquals(new BigDecimal(50000), reserva.getValor());
    }

    @Test
    @DisplayName("Deberia aplicar el descuento a la tarifa por reservas acumulado")
    void deberiaAplicarDescuentoPorReservasAcumulado() {
        // arrange
        LocalDateTime fechaReserva = LocalDateTime.now().plusDays(3).withHour(12);
        Reserva reserva = new ReservaTestDataBuilder().conId(1L).conFechaReserva(fechaReserva).build();
        DtoUsuario dtoUsuario = new UsuarioTestDataBuilder().conId(1L).conReservasAcumulado(3).buildDto();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        Mockito.when(repositorioUsuario.existePorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioReserva.existePorFechaReservaYUsuarioId(Mockito.any(), Mockito.anyLong())).thenReturn(false);
        Mockito.when(repositorioUsuario.obtenerTarifaPorUsuarioId(Mockito.anyLong())).thenReturn(new BigDecimal(50000));
        Mockito.when(repositorioUsuario.encontrarPorId(Mockito.anyLong())).thenReturn(dtoUsuario);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva, repositorioUsuario);
        // act
        servicioCrearReserva.ejecutar(reserva);
        // assert
        assertEquals(new BigDecimal("35000.0"), reserva.getValor());
    }

    @Test
    @DisplayName("Deberia aplicar el descuento a la tarifa por la hora de reservacion")
    void deberiaAplicarDescuentoPorHoraReserva() {
        // arrange
        LocalDateTime fechaReserva = LocalDateTime.now().plusDays(3).withHour(19);
        Reserva reserva = new ReservaTestDataBuilder().conId(1L).conFechaReserva(fechaReserva).build();
        DtoUsuario dtoUsuario = new UsuarioTestDataBuilder().conId(1L).buildDto();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        Mockito.when(repositorioUsuario.existePorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioReserva.existePorFechaReservaYUsuarioId(Mockito.any(), Mockito.anyLong())).thenReturn(false);
        Mockito.when(repositorioUsuario.obtenerTarifaPorUsuarioId(Mockito.anyLong())).thenReturn(new BigDecimal(50000));
        Mockito.when(repositorioUsuario.encontrarPorId(Mockito.anyLong())).thenReturn(dtoUsuario);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva, repositorioUsuario);
        // act
        servicioCrearReserva.ejecutar(reserva);
        // assert
        assertEquals(new BigDecimal("40000.0"), reserva.getValor());
    }


    @Test
    @DisplayName("Deberia Crear el usuario de manera correcta")
    void deberiaCrearElUsuarioDeManeraCorrecta() {

        // arrange
        Reserva reserva = new ReservaTestDataBuilder().build();
        DtoUsuario dtoUsuario = new UsuarioTestDataBuilder().conId(1L).buildDto();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        Mockito.when(repositorioUsuario.existePorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioReserva.existePorFechaReservaYUsuarioId(Mockito.any(), Mockito.anyLong())).thenReturn(false);
        Mockito.when(repositorioUsuario.encontrarPorId(Mockito.anyLong())).thenReturn(dtoUsuario);
        Mockito.when(repositorioUsuario.obtenerTarifaPorUsuarioId(Mockito.anyLong())).thenReturn(new BigDecimal(50000));
        Mockito.when(repositorioReserva.crear(reserva)).thenReturn(10L);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva, repositorioUsuario);
        // act
        Long idReserva = servicioCrearReserva.ejecutar(reserva);
        //- assert
        assertEquals(10L,idReserva);
        Mockito.verify(repositorioReserva, Mockito.times(1)).crear(reserva);
    }
}
