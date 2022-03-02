package com.ceiba.reserva.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoReserva implements RowMapper<DtoReserva>, MapperResult {

	private final boolean isReservaEstadoNombre;

	public MapeoReserva(boolean isReservaEstadoNombre) {
		this.isReservaEstadoNombre = isReservaEstadoNombre;
	}

	public MapeoReserva() {
		this.isReservaEstadoNombre = false;
	}


    @Override
    public DtoReserva mapRow(ResultSet resultSet, int rowNum) throws SQLException {

    	Long id = resultSet.getLong("id");
    	BigDecimal valor = resultSet.getBigDecimal("valor");
    	LocalDateTime fechaCreacion = extraerLocalDateTime(resultSet, "fecha_creacion");
    	LocalDateTime fechaReserva = extraerLocalDateTime(resultSet, "fecha_reserva");
    	Long usuarioId = resultSet.getLong("usuario_id");
		Long reservaEstadoId = resultSet.getLong("reserva_estado_id");
		String reservaEstadoNombre = null;

		if (isReservaEstadoNombre)
			reservaEstadoNombre = resultSet.getString("reserva_estado_nombre");

        return new DtoReserva(id,valor,fechaCreacion,fechaReserva,usuarioId,reservaEstadoId,reservaEstadoNombre);
    }

}