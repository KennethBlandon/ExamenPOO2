package com.example.controlasistencia

@Composable
fun ControlAsistenciaScreen(modifier: Modifier = Modifier) {
    var nombreEstudiante by remember { mutableStateOf("") }
    var estadoSeleccionado by remember { mutableStateOf(EstadoAsistencia.PRESENTE) }
    var mensajeConfirmacion by remember { mutableStateOf("Aun no hay registros.") }
    val registros = remember { mutableStateListOf<RegistroAsistencia>() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Control de Asistencia",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = nombreEstudiante
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