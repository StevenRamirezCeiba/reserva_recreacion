select count(1) from reserva
where DATE(fecha_reserva) = :fechaReserva
and usuario_id = :usuarioId
and reserva_estado_id = 1