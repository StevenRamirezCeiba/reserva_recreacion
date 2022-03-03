package com.ceiba.reserva.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reserva.adaptador.dao.MapeoReserva;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public class RepositorioReservaMysql implements RepositorioReserva {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="reserva", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace="reserva", value="actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace="reserva", value="existePorFechaReservaYUsuarioId")
    private static String sqlExistePorFechaReservaYUsuarioId;

    @SqlStatement(namespace = "reserva", value = "existePorId")
    private static String sqlExistePorId;

    @SqlStatement(namespace = "reserva", value = "listarPorUsuarioNumeroDocumento")
    private static String sqlListarPorUsuarioNumeroDocumento;

    public RepositorioReservaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Reserva reserva) {
        return this.customNamedParameterJdbcTemplate.crear(reserva, sqlCrear);
    }

    @Override
    public void actualizar(Reserva reserva) {
        this.customNamedParameterJdbcTemplate.actualizar(reserva, sqlActualizar);
    }

    @Override
    public boolean existePorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorId,paramSource, Boolean.class);
    }

    @Override
    public boolean existePorFechaReservaYUsuarioId(LocalDate fechaReserva, Long usuarioId) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("fechaReserva", fechaReserva);
        paramSource.addValue("usuarioId", usuarioId);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorFechaReservaYUsuarioId,paramSource, Boolean.class);
    }

    @Override
    public List<DtoReserva> listarPorUsuarioNumeroDocumento(Long numeroDocumento) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("numeroDocumento", numeroDocumento);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarPorUsuarioNumeroDocumento, parameterSource, new MapeoReserva());
    }
}