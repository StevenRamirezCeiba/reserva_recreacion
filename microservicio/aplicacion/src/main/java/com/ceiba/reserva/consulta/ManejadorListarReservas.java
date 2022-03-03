package com.ceiba.reserva.consulta;

import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.servicio.ServicioListarReserva;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarReservas {
    private final ServicioListarReserva servicioListarReserva;

    public ManejadorListarReservas(ServicioListarReserva servicioListarReserva){
        this.servicioListarReserva = servicioListarReserva;
    }

    public List<DtoReserva> listarReservasPorUsuarioNumeroDocumento(Long numeroDocumento){
        return this.servicioListarReserva.listarReservasPorUsuarioNumeroDocumento(numeroDocumento);
    }
    
}
