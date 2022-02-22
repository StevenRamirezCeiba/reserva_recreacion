package com.ceiba.usuario.servicio;

import java.math.BigDecimal;
import java.time.LocalTime;


import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.usuario.modelo.entidad.Reserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioReserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;

public class ServicioCrearReserva {
	
	private static final String YA_EXISTE_UNA_RESERVA_CREADA_PARA_LA_FECHA_SELECCIONADA = "Ya existe una reserva creada para la fecha seleccionada";

	private static final int CANTIDAD_RESERVAS_ACUMULADO_PARA_DESCUENTO = 3;
	private static final BigDecimal VALOR_DESCUENTO_RESERVAS_ACUMULADO = new BigDecimal(30);
	private static final BigDecimal VALOR_DESCUENTO_HORA_RESERVA = new BigDecimal(20);

	private final RepositorioReserva repositorioReserva;
    
    private final RepositorioUsuario repositorioUsuario;

    public ServicioCrearReserva(RepositorioReserva repositorioReserva, RepositorioUsuario repositorioUsuario) {
        this.repositorioReserva = repositorioReserva;
        this.repositorioUsuario = repositorioUsuario;
    }

    public Long ejecutar(Reserva reserva) {
    	validarExistenciaPreviaPorFechaReservaYUsuarioId(reserva);
        DtoUsuario dtoUsuario = this.repositorioUsuario.encontrarPorId(reserva.getUsuarioId());
    	boolean esValidarPorReservasAcumulado = validarDescuentoPorReservasAcumulado(reserva, dtoUsuario);
    	validarDescuentoPorHoraReserva(reserva);
    	
    	Long id = this.repositorioReserva.crear(reserva);
    	validarActualizacionReservaAcumulador(esValidarPorReservasAcumulado, dtoUsuario);
   
        return id;
    }

    private void validarExistenciaPreviaPorFechaReservaYUsuarioId(Reserva reserva) {
        boolean existe = this.repositorioReserva.existePorFechaReservaYUsuarioId(reserva.getFechaReserva().toLocalDate(), reserva.getUsuarioId());
        if(existe) {
            throw new ExcepcionDuplicidad(YA_EXISTE_UNA_RESERVA_CREADA_PARA_LA_FECHA_SELECCIONADA);
        }
    }
    
    private boolean validarDescuentoPorReservasAcumulado(Reserva reserva, DtoUsuario dtoUsuario) {
        if(dtoUsuario.getReservasAcumulado().equals(CANTIDAD_RESERVAS_ACUMULADO_PARA_DESCUENTO)) {
        	reserva.realizarDescuento(VALOR_DESCUENTO_RESERVAS_ACUMULADO);
        	return true;
        }
        
        return false;
    }
    
    private void validarDescuentoPorHoraReserva(Reserva reserva) {
    	LocalTime fechaReserva = reserva.getFechaReserva().toLocalTime();
    	LocalTime horaParaDescuento = LocalTime.parse("18:00:00");
    	if (fechaReserva.isAfter(horaParaDescuento)) {
    		reserva.realizarDescuento(VALOR_DESCUENTO_HORA_RESERVA);
    	}
    }
    
    private void validarActualizacionReservaAcumulador(boolean esValidarPorReservasAcumulado, DtoUsuario dtoUsuario) {
    	Integer reservasAcumulador = 0;
    	if(!esValidarPorReservasAcumulado) {
    		reservasAcumulador += dtoUsuario.getReservasAcumulado() + 1;
    	}
    	this.repositorioUsuario.actualizarReservasAcumulado(dtoUsuario.getId(), reservasAcumulador);
    }
}
