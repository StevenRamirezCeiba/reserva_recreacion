package com.ceiba.reserva.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoReserva implements RowMapper<DtoReserva>, MapperResult {

    @Override
    public DtoReserva mapRow(ResultSet resultSet, int rowNum) throws SQLException {

    	Long id = resultSet.getLong("id");
    	BigDecimal valor = resultSet.getBigDecimal("valor");
    	LocalDateTime fechaCreacion = extraerLocalDateTime(resultSet, "fecha_creacion");
    	LocalDateTime fechaReserva = extraerLocalDateTime(resultSet, "fecha_reserva");
    	Long usuarioId = resultSet.getLong("usuario_id");

        return new DtoReserva(id,valor,fechaCreacion,fechaReserva,usuarioId);
    }

}