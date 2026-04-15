package ni.edu.uam.registroasistencia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ni.edu.uam.registroasistencia.ui.theme.RegistroAsistenciaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegistroAsistenciaTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background
                ) { innerPadding ->
                    ControlAsistenciaScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

data class RegistroAsistencia(
    val nombre: String,
    val estado: EstadoAsistencia
)

enum class EstadoAsistencia {
    PRESENTE,
    AUSENTE,
    TARDE
}

private fun EstadoAsistencia.label(): String = when (this) {
    EstadoAsistencia.PRESENTE -> "Presente"
    EstadoAsistencia.AUSENTE -> "Ausente"
    EstadoAsistencia.TARDE -> "Tarde"
}

private fun EstadoAsistencia.cardColor(): Color = when (this) {
    EstadoAsistencia.PRESENTE -> Color(0xFFC8E6C9)
    EstadoAsistencia.AUSENTE -> Color(0xFFFFCDD2)
    EstadoAsistencia.TARDE -> Color(0xFFFFF9C4)
}

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
            value = nombreEstudiante,
            onValueChange = { nombreEstudiante = it },
            label = { Text("Nombre del estudiante") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

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

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(registros) { index, registro ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = registro.estado.cardColor(),
                        contentColor = Color.Black
                    )
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "${index + 1}. ${registro.nombre}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "Estado: ${registro.estado.label()}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            if (registros.isEmpty()) {
                item {
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "No hay estudiantes registrados todavia.",
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RegistroAsistenciaTheme {
        ControlAsistenciaScreen(modifier = Modifier.height(700.dp))
    }
}