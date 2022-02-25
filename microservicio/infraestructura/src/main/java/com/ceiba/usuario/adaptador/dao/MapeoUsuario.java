package com.ceiba.usuario.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import org.springframework.jdbc.core.RowMapper;

public class MapeoUsuario implements RowMapper<DtoUsuario>, MapperResult {

    private final boolean isCategoriaNombre;

    public MapeoUsuario(boolean isCategoriaNombre) {
        this.isCategoriaNombre = isCategoriaNombre;
    }

    public MapeoUsuario() {
        this.isCategoriaNombre = false;
    }

    @Override
    public DtoUsuario mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        String apellido = resultSet.getString("apellido");
        Long numeroDocumento = resultSet.getLong("numero_documento");
        Integer reservasAcumulado = resultSet.getInt("reservas_acumulado");
        Long categoriaId = resultSet.getLong("categoria_id");
        String categoriaNombre = null;
        LocalDateTime fecha = extraerLocalDateTime(resultSet, "fecha_creacion");

        if (isCategoriaNombre)
            categoriaNombre = resultSet.getString("categoria_nombre");


        return new DtoUsuario(id,nombre,apellido,numeroDocumento,reservasAcumulado,categoriaId,categoriaNombre,fecha);
    }

}
