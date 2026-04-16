package com.example.controlasistencia

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
}}