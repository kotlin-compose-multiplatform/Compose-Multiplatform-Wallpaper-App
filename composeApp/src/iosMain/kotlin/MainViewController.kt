import androidx.compose.ui.window.ComposeUIViewController
import features.images.di.imagesModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App() }

fun initKoin() {
    startKoin {
        modules(imagesModule)
    }
}