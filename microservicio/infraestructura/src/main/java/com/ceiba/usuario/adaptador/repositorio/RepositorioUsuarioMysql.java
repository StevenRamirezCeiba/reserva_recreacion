package com.ceiba.usuario.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.usuario.adaptador.dao.MapeoUsuario;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class RepositorioUsuarioMysql implements RepositorioUsuario {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="usuario", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace="usuario", value="actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace="usuario", value="eliminar")
    private static String sqlEliminar;

    @SqlStatement(namespace="usuario", value="existePorNumeroDocumento")
    private static String sqlExistePorNumeroDocumento;

    @SqlStatement(namespace="usuario", value="existePorId")
    private static String sqlExistePorId;
    
    @SqlStatement(namespace="usuario", value="encontrarPorId")
    private static String sqlEncontrarPorId;
    
    @SqlStatement(namespace="usuario", value="actualizarReservasAcumulado")
    private static String sqlActualizarReservasAcumulado;

    @SqlStatement(namespace="usuario", value="obtenerTarifaPorUsuarioId")
    private static String sqlObtenerTarifaPorUsuarioId;

    public RepositorioUsuarioMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Usuario usuario) {
        return this.customNamedParameterJdbcTemplate.crear(usuario, sqlCrear);
    }

    @Override
    public boolean existePorNumeroDocumento(Long numeroDocumento) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("numeroDocumento", numeroDocumento);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorNumeroDocumento,paramSource, Boolean.class);
    }

    @Override
    public void actualizar(Usuario usuario) {
        this.customNamedParameterJdbcTemplate.actualizar(usuario, sqlActualizar);
    }

    @Override
    public boolean existePorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
       
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorId,paramSource, Boolean.class);
    }
    
    @Override
    public DtoUsuario encontrarPorId(Long id) {
    	MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlEncontrarPorId, paramSource, new MapeoUsuario());
    }
    
    @Override
    public void actualizarReservasAcumulado(Long id, Integer reservasAcumulado) {
    	MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        paramSource.addValue("reservasAcumulado", reservasAcumulado);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlActualizarReservasAcumulado, paramSource);
    }

    @Override
    public BigDecimal obtenerTarifaPorUsuarioId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerTarifaPorUsuarioId, paramSource, BigDecimal.class);

    }
}
