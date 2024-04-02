package features.videos.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object VideosTab: Tab {
    @Composable
    override fun Content() {
        VideosScreen()
    }

    override val options: TabOptions
        @Composable
        get() {
            val title = "Videos"
            val icon = rememberVectorPainter(Icons.Default.MoreVert)
            
            return TabOptions(
                index = 1u,
                title = title,
                icon = icon
            )
        }
}