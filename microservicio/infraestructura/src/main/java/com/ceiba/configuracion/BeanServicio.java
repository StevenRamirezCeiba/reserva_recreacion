package com.ceiba.configuracion;

import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.ServicioActualizarReserva;
import com.ceiba.reserva.servicio.ServicioListarReserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioActualizarUsuario;
import com.ceiba.reserva.servicio.ServicioCrearReserva;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import com.ceiba.usuario.servicio.ServicioListarUsuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearUsuario servicioCrearUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioCrearUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioActualizarUsuario servicioActualizarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioActualizarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioListarUsuario servicioListarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioListarUsuario(repositorioUsuario);
    }
	
    @Bean
    public ServicioCrearReserva servicioCrearReserva(RepositorioReserva repositorioReserva, RepositorioUsuario repositorioUsuario) {
    	return new ServicioCrearReserva(repositorioReserva, repositorioUsuario);
    }

    @Bean
    public ServicioActualizarReserva servicioActualizarReserva(RepositorioReserva repositorioReserva) {
        return new ServicioActualizarReserva(repositorioReserva);
    }

    @Bean
    public ServicioListarReserva servicioListarReserva(RepositorioReserva repositorioReserva, ServicioListarUsuario servicioListarUsuario) {
        return new ServicioListarReserva(repositorioReserva, servicioListarUsuario);
    }
}
