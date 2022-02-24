package com.ceiba.reserva.servicio;

import java.math.BigDecimal;
import java.time.LocalTime;


import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;

public class ServicioCrearReserva {
	
	private static final String YA_EXISTE_UNA_RESERVA_CREADA_PARA_LA_FECHA_SELECCIONADA = "Ya existe una reserva creada para la fecha seleccionada";
    private static final String NO_SE_ENCONTRO_EL_USUARIO_EN_EL_SISTEMA = "No se encontró el usuario en el sistema";

	private static final int CANTIDAD_RESERVAS_ACUMULADO_PARA_DESCUENTO = 3;
	private static final BigDecimal VALOR_DESCUENTO_RESERVAS_ACUMULADO = new BigDecimal(30);
	private static final BigDecimal VALOR_DESCUENTO_HORA_RESERVA = new BigDecimal(20);
    private static final String HORA_APLICACION_DESCUENTO = "18:00:00";

	private final RepositorioReserva repositorioReserva;
    private final RepositorioUsuario repositorioUsuario;

    public ServicioCrearReserva(RepositorioReserva repositorioReserva, RepositorioUsuario repositorioUsuario) {
        this.repositorioReserva = repositorioReserva;
        this.repositorioUsuario = repositorioUsuario;
    }

    public Long ejecutar(Reserva reserva) {
        validarExistenciaUsuarioPorId(reserva);
        validarExistenciaPreviaPorFechaReservaYUsuarioId(reserva);
        obtenerTarifaPorUsuario(reserva);
    	validarDescuentoPorReservasAcumulado(reserva);
    	validarDescuentoPorHoraReserva(reserva);

        return this.repositorioReserva.crear(reserva);
    }

    private void validarExistenciaUsuarioPorId(Reserva reserva) {
        boolean existe = repositorioUsuario.existePorId(reserva.getUsuarioId());
        if (!existe) {
            throw new ExcepcionSinDatos(NO_SE_ENCONTRO_EL_USUARIO_EN_EL_SISTEMA);
        }
    }

    private void validarExistenciaPreviaPorFechaReservaYUsuarioId(Reserva reserva) {
        boolean existe = this.repositorioReserva.existePorFechaReservaYUsuarioId(reserva.getFechaReserva().toLocalDate(), reserva.getUsuarioId());
        if(existe) {
            throw new ExcepcionDuplicidad(YA_EXISTE_UNA_RESERVA_CREADA_PARA_LA_FECHA_SELECCIONADA);
        }
    }

    private void obtenerTarifaPorUsuario(Reserva reserva) {
        BigDecimal valor = repositorioUsuario.obtenerTarifaPorUsuarioId(reserva.getUsuarioId());
        reserva.setValor(valor);
    }
    
    private void validarDescuentoPorReservasAcumulado(Reserva reserva) {
        Integer reservasAcumulador = 0;
        DtoUsuario dtoUsuario = this.repositorioUsuario.encontrarPorId(reserva.getUsuarioId());

        if(dtoUsuario.getReservasAcumulado().equals(CANTIDAD_RESERVAS_ACUMULADO_PARA_DESCUENTO)) {
        	reserva.realizarDescuento(VALOR_DESCUENTO_RESERVAS_ACUMULADO);
        } else {
            reservasAcumulador += dtoUsuario.getReservasAcumulado() + 1;
        }

        this.repositorioUsuario.actualizarReservasAcumulado(reserva.getUsuarioId(), reservasAcumulador);
    }
    
    private void validarDescuentoPorHoraReserva(Reserva reserva) {
    	LocalTime fechaReserva = reserva.getFechaReserva().toLocalTime();
    	LocalTime horaParaDescuento = LocalTime.parse(HORA_APLICACION_DESCUENTO);
    	if (fechaReserva.isAfter(horaParaDescuento)) {
    		reserva.realizarDescuento(VALOR_DESCUENTO_HORA_RESERVA);
    	}
    }
}
