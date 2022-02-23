package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.usuario.comando.ComandoUsuario;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ComandoReservaTestDataBuilder {

    private Long id;
    private BigDecimal valor;
    private LocalDateTime fechaReserva;
    private Long usuarioId;
    private Long reservaEstadoId;

    public ComandoReservaTestDataBuilder() {
        valor = new BigDecimal(50000);
        fechaReserva = LocalDateTime.now().plusDays(5);
        usuarioId = 1L;
        reservaEstadoId = 1L;
    }

    public ComandoReservaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ComandoReservaTestDataBuilder conValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public ComandoReservaTestDataBuilder conFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
        return this;
    }

    public ComandoReservaTestDataBuilder conUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
        return this;
    }

    public ComandoReservaTestDataBuilder conReservaEstadoId(Long reservaEstadoId) {
        this.reservaEstadoId = reservaEstadoId;
        return this;
    }

    public ComandoReserva build() {
        return new ComandoReserva(id, valor, fechaReserva, usuarioId, reservaEstadoId);
    }
}
