package com.ceiba.usuario.servicio.testdatabuilder;

import com.ceiba.usuario.comando.ComandoUsuario;

public class ComandoUsuarioTestDataBuilder {

	private Long id;
    private String nombre;
    private String apellido;
	private String clave;
	private Long numeroDocumento;
	private Integer reservasAcumulado;
	private Long categoriaId;

    public ComandoUsuarioTestDataBuilder() {
        nombre = "christian";
    	apellido = "ramirez";
        clave = "1234";
        numeroDocumento = 1075318997L;
        reservasAcumulado = 0;
        categoriaId = 1L;
    }

    public ComandoUsuarioTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ComandoUsuario build() {
        return new ComandoUsuario(id, nombre, apellido, clave, numeroDocumento, reservasAcumulado, categoriaId);
    }
}
