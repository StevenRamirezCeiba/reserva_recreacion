package com.ceiba.usuario.servicio.testdatabuilder;

import com.ceiba.usuario.comando.ComandoUsuario;

public class ComandoUsuarioTestDataBuilder {

	private Long id;
    private String nombre;
    private final String apellido;
	private final String clave;
	private final Long numeroDocumento;
	private final Integer reservasAcumulado;
	private final Long categoriaId;

    public ComandoUsuarioTestDataBuilder() {
        nombre = "christian";
    	apellido = "ramirez";
        clave = "1234";
        numeroDocumento = 101010L;
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
