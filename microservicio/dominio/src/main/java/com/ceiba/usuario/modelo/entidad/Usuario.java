package com.ceiba.usuario.modelo.entidad;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.validarLongitud;
import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.dominio.ValidadorArgumento.validarLongitudMaxima;

@Getter
@ToString
public class Usuario {

	private static final String LA_CLAVE_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A = "La clave debe tener una longitud mayor o igual a %s";
	private static final String SE_DEBE_INGRESAR_LA_CLAVE = "Se debe ingresar la clave";
	private static final String SE_DEBE_INGRESAR_EL_NOMBRE = "Se debe ingresar el nombre";
	private static final String SE_DEBE_INGRESAR_EL_APELLIDO = "Se debe ingresar el apellido";
	private static final String EL_NUMERO_DE_DOCUMENTO_DEBE_TENER_UNA_LONGITUD_MENOR_O_IGUAL_A = "El numero de documento debe tener una longitud menor o igual a %s";
	private static final String SE_DEBE_INGRESAR_EL_NUMERO_DE_DOCUMENTO = "Se debe ingresar el numero de documento";
	private static final String EL_ACUMULADO_RESERVAS_DEBE_TENER_UNA_LONGITUD_MENOR_O_IGUAL_A = "El acumulado de reservas debe tener una longitud menor o igual a %s";
	private static final String SE_DEBE_INGRESAR_EL_ACUMULADO_DE_RESERVAS = "Se debe ingresar el acumulado de reservas";
	private static final String SE_DEBE_INGRESAR_LA_CATEGORIA = "Se debe ingresar la categoria del usuario";

	private static final int LONGITUD_MINIMA_CLAVE = 4;
	private static final int LONGITUD_MAXIMA_NUMERO_DOCUMENTO = 10;
	private static final int LONGITUD_MAXIMA_RESERVAS_ACUMULADO = 1;

	private Long id;
	private String nombre;
	private String apellido;
	private String clave;
	private Long numeroDocumento;
	private Integer reservasAcumulado;
	private Long categoriaId;
	private LocalDateTime fechaCreacion;

	public Usuario(Long id, String nombre, String apellido, String clave, Long numeroDocumento,
			Integer reservasAcumulado, Long categoriaId) {
		validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE);
		validarObligatorio(apellido, SE_DEBE_INGRESAR_EL_APELLIDO);
		validarObligatorio(clave, SE_DEBE_INGRESAR_LA_CLAVE);
		validarLongitud(clave, LONGITUD_MINIMA_CLAVE,
				String.format(LA_CLAVE_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A, LONGITUD_MINIMA_CLAVE));
		validarObligatorio(numeroDocumento, SE_DEBE_INGRESAR_EL_NUMERO_DE_DOCUMENTO);
		validarLongitudMaxima(numeroDocumento, LONGITUD_MAXIMA_NUMERO_DOCUMENTO,
				String.format(EL_NUMERO_DE_DOCUMENTO_DEBE_TENER_UNA_LONGITUD_MENOR_O_IGUAL_A, LONGITUD_MAXIMA_NUMERO_DOCUMENTO));
		validarObligatorio(reservasAcumulado, SE_DEBE_INGRESAR_EL_ACUMULADO_DE_RESERVAS);
		validarLongitudMaxima(reservasAcumulado, LONGITUD_MAXIMA_RESERVAS_ACUMULADO,
				String.format(EL_ACUMULADO_RESERVAS_DEBE_TENER_UNA_LONGITUD_MENOR_O_IGUAL_A, LONGITUD_MAXIMA_RESERVAS_ACUMULADO));
		validarObligatorio(categoriaId, SE_DEBE_INGRESAR_LA_CATEGORIA);

		this.id = id;
		this.nombre = nombre.toUpperCase();
		this.apellido = apellido.toUpperCase();
		this.clave = clave;
		this.numeroDocumento = numeroDocumento;
		this.reservasAcumulado = reservasAcumulado;
		this.categoriaId = categoriaId;
		this.fechaCreacion = LocalDateTime.now();
	}

}
