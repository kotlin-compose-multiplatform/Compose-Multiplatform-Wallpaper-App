package features.videos.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

class VideosScreen: Screen {
    @Composable
    override fun Content() {
        Column {
            Text("Videos")
        }
    }

}