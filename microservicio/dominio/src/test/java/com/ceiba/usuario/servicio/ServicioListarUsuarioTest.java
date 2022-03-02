package com.ceiba.usuario.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioListarUsuarioTest {

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando el usuario no exista")
    void deberiaLanzarUnaExepcionCuandoElUsuarioNoExista() {
        // arrange
        Long numeroDocumento = 1075318997L;
        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        Mockito.when(repositorioUsuario.existePorNumeroDocumento(Mockito.anyLong())).thenReturn(false);
        ServicioListarUsuario servicioListarUsuario = new ServicioListarUsuario(repositorioUsuario);
        // act - assert
        BasePrueba.assertThrows(() -> servicioListarUsuario.encontrarPorNumeroDocumento(numeroDocumento), ExcepcionSinDatos.class,"El usuario con este número de documento no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia obtener el usuario de manera correcta")
    void deberiaObtenerElUsuarioDeManeraCorrecta() {
        // arrange
        Long numeroDocumento = 1075318997L;
        DtoUsuario dtoUsuario = new UsuarioTestDataBuilder().conId(1L).buildDto();
        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        Mockito.when(repositorioUsuario.existePorNumeroDocumento(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioUsuario.encontrarPorNumeroDocumento(Mockito.anyLong())).thenReturn(dtoUsuario);
        ServicioListarUsuario servicioListarUsuario = new ServicioListarUsuario(repositorioUsuario);
        // act
        DtoUsuario dtoUsuarioResponse = servicioListarUsuario.encontrarPorNumeroDocumento(numeroDocumento);
        //- assert
        assertEquals(dtoUsuario, dtoUsuarioResponse);
        Mockito.verify(repositorioUsuario, Mockito.times(1)).encontrarPorNumeroDocumento(numeroDocumento);
    }
}
