update reserva
set valor = :valor,
	fecha_reserva = :fechaReserva,
	usuario_id = :usuarioId,
	reserva_estado_id = reservaEstadoId
where id = :id;