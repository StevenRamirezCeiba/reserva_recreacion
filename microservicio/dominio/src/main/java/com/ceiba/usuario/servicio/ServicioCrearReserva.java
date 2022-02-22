package com.ceiba.usuario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;

public class ServicioCrearReserva {
	
	private static final String YA_EXISTE_UNA_RESERVA_CREADA_PARA_LA_FECHA_SELECCIONADA = "Ya existe una reserva creada para la fecha seleccionada";

	private static final int CANTIDAD_RESERVAS_ACUMULADO_PARA_DESCUENTO = 3;
	private static final int VALOR_DESCUENTO_RESERVAS_ACUMULADO = 30;

	private final RepositorioReserva repositorioReserva;
    
    private final RepositorioUsuario repositorioUsuario;

    public ServicioCrearReserva(RepositorioReserva repositorioReserva, RepositorioUsuario repositorioUsuario) {
        this.repositorioReserva = repositorioReserva;
        this.repositorioUsuario = repositorioUsuario;
    }

    public Long ejecutar(Reserva reserva) {
    	validarExistenciaPreviaPorFechaReserva(reserva);
    	validarDescuentoPorReservasAcumulado(reserva);
    	
        return this.repositorioReserva.crear(reserva);
    }

    private void validarExistenciaPreviaPorFechaReserva(Reserva reserva) {
        boolean existe = this.repositorioReserva.existePorFechaReservaYUsuarioId(reserva.getFechaReserva().toLocalDate(), reserva.getUsuarioId());
        if(existe) {
            throw new ExcepcionDuplicidad(YA_EXISTE_UNA_RESERVA_CREADA_PARA_LA_FECHA_SELECCIONADA);
        }
    }
    
    private void validarDescuentoPorReservasAcumulado(Reserva reserva) {
        DtoUsuario dtoUsuario = this.repositorioUsuario.encontrarPorId(reserva.getUsuarioId());
        if(dtoUsuario.getReservasAcumulado().equals(CANTIDAD_RESERVAS_ACUMULADO_PARA_DESCUENTO)) {
        	reserva.descuentoPorReservasAcumulado(VALOR_DESCUENTO_RESERVAS_ACUMULADO);
        	this.repositorioUsuario.reinicioReservasAcumulado(dtoUsuario.getId());
        }
    }
}
