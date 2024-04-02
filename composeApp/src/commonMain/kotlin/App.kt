import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import ui.app.Router

@Composable
@Preview
fun App() {
    KoinContext {
        MaterialTheme {
            Router()
        }
    }
}