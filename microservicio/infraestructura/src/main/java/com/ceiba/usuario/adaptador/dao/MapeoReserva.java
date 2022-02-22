package com.ceiba.usuario.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.usuario.modelo.dto.DtoReserva;

public class MapeoReserva implements RowMapper<DtoReserva>, MapperResult {

    @Override
    public DtoReserva mapRow(ResultSet resultSet, int rowNum) throws SQLException {

    	Long id = resultSet.getLong("id");
    	Long valor = resultSet.getLong("valor");
    	LocalDateTime fechaCreacion = extraerLocalDateTime(resultSet, "fecha_creacion");
    	LocalDateTime fechaReserva = extraerLocalDateTime(resultSet, "fecha_reserva");
    	Long usuarioId = resultSet.getLong("usuario_id");;

        return new DtoReserva(id,valor,fechaCreacion,fechaReserva,usuarioId);
    }

}