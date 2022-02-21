package com.ceiba.usuario.servicio.testdatabuilder;

import com.ceiba.usuario.modelo.entidad.Usuario;


public class UsuarioTestDataBuilder {

	private Long id;
	private String nombre;
	private String apellido;
	private String clave;
	private Long numeroDocumento;
	private Integer reservasAcumulado;
	private Long categoriaId;

    public UsuarioTestDataBuilder() {
    	nombre = "christian";
    	apellido = "ramirez";
        clave = "1234";
        numeroDocumento = 1075318997L;
        reservasAcumulado = 0;
        categoriaId = 1L;
    }

    public UsuarioTestDataBuilder conClave(String clave) {
        this.clave = clave;
        return this;
    }

    public UsuarioTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public UsuarioTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
    
    public UsuarioTestDataBuilder conApellido(String apellido) {
    	this.apellido = apellido;
    	return this;
    }
    
    public UsuarioTestDataBuilder conNumeroDocumento(Long numeroDocumento) {
    	this.numeroDocumento = numeroDocumento;
    	return this;
    }
    
    public UsuarioTestDataBuilder conReservasAcumulado(Integer reservasAcumulado) {
    	this.reservasAcumulado = reservasAcumulado;
    	return this;
    }
    
    public UsuarioTestDataBuilder conCategoriaId(Long categoriaId) {
    	this.categoriaId = categoriaId;
    	return this;
    }

    public Usuario build() {
        return new Usuario(id, nombre, apellido, clave, numeroDocumento, reservasAcumulado, categoriaId);
    }
}
