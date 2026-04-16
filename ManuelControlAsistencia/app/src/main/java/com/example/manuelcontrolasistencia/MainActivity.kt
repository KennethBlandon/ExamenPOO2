package com.example.manuelcontrolasistencia

Text(text = "Estado", style = MaterialTheme.typography.titleMedium)
EstadoAsistencia.entries.forEach { estado ->
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = estadoSeleccionado == estado,
            onClick = { estadoSeleccionado = estado }
        )
        Text(text = estado.label())
    }
}

Button(
onClick = {
    val nombreLimpio = nombreEstudiante.trim()
    if (nombreLimpio.isBlank()) {
        mensajeConfirmacion = "Ingresa un nombre antes de registrar."
        return@Button
    }

    val registro = RegistroAsistencia(nombreLimpio, estadoSeleccionado)
    registros.add(0, registro)
    mensajeConfirmacion = "Registrado: ${registro.nombre} - ${registro.estado.label()}"
    nombreEstudiante = ""
    estadoSeleccionado = EstadoAsistencia.PRESENTE
},
modifier = Modifier.fillMaxWidth()
) {
    Text("Registrar asistencia")
}

Text(
text = mensajeConfirmacion,
style = MaterialTheme.typography.bodyMedium,
color = MaterialTheme.colorScheme.primary
)