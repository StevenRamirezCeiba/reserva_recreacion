package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioListarUsuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioListarReservaTest {

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando el usuario no exista")
    void deberiaLanzarUnaExepcionCuandoElUsuarioNoExista() {
        // arrange
        Long numeroDocumento = 1075318997L;
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        Mockito.when(repositorioUsuario.existePorNumeroDocumento(Mockito.anyLong())).thenReturn(false);

        ServicioListarUsuario servicioListarUsuario = new ServicioListarUsuario(repositorioUsuario);
        ServicioListarReserva servicioListarReserva = new ServicioListarReserva(repositorioReserva, servicioListarUsuario);
        // act - assert
        BasePrueba.assertThrows(() -> servicioListarReserva.listarReservasPorUsuarioNumeroDocumento(numeroDocumento), ExcepcionSinDatos.class,"El usuario con este número de documento no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia obtener el usuario de manera correcta")
    void deberiaObtenerLasReservasDeManeraCorrecta() {
        // arrange
        Long numeroDocumento = 1075318997L;
        List<DtoReserva> dtoReservas = Arrays.asList(new ReservaTestDataBuilder().conId(1L).buildDto());
        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioUsuario.existePorNumeroDocumento(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioReserva.listarPorUsuarioNumeroDocumento(Mockito.anyLong())).thenReturn(dtoReservas);
        ServicioListarUsuario servicioListarUsuario = new ServicioListarUsuario(repositorioUsuario);
        ServicioListarReserva servicioListarReserva = new ServicioListarReserva(repositorioReserva, servicioListarUsuario);
        // act
        List<DtoReserva> dtoReservasResponse = servicioListarReserva.listarReservasPorUsuarioNumeroDocumento(numeroDocumento);
        //- assert
        assertEquals(dtoReservas, dtoReservasResponse);
        Mockito.verify(repositorioReserva, Mockito.times(1)).listarPorUsuarioNumeroDocumento(numeroDocumento);
    }
}