package com.ceiba.reserva.consulta;

import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.servicio.ServicioListarReserva;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarReservas {

    private final DaoReserva daoReserva;
    private final ServicioListarReserva servicioListarReserva;

    public ManejadorListarReservas(DaoReserva daoReserva, ServicioListarReserva servicioListarReserva){
        this.daoReserva = daoReserva;
        this.servicioListarReserva = servicioListarReserva;
    }

    public List<DtoReserva> ejecutar(){ return this.daoReserva.listar(); }

    public List<DtoReserva> listarReservasPorUsuarioNumeroDocumento(Long numeroDocumento){
        return this.servicioListarReserva.listarReservasPorUsuarioNumeroDocumento(numeroDocumento);
    }
    
}
