package ni.edu.uam.kennethexamen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ni.edu.uam.kennethexamen.ui.theme.KennethExamenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KennethExamenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/*LazyColumn(
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KennethExamenTheme {
        Greeting("Android")
    }
}