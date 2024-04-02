package ui.app

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import features.images.presentation.ui.ImagesTab
import features.videos.presentation.ui.VideosTab


@Composable
fun Router(modifier: Modifier = Modifier) {
    TabNavigator(ImagesTab) {
        Scaffold(
            content = {
                CurrentTab()
                      },
            bottomBar = {
                BottomNavigation(
                    backgroundColor = Color.White
                ) {
                    TabNavigationItem(ImagesTab)
                    TabNavigationItem(VideosTab)
                }
            }
        )
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = { tab.options.icon?.let { Icon(painter = it, contentDescription = tab.options.title) } }
    )
}