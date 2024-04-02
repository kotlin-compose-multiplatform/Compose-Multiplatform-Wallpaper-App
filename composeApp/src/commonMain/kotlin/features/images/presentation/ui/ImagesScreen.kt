package features.images.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import common.changeWallpaper
import features.images.presentation.viewmodel.ImagesScreenModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.getKoin
import pexels.composeapp.generated.resources.Res
import pexels.composeapp.generated.resources.landscape_placeholder_svgrepo_com

class ImagesScreen : Screen {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val context = LocalPlatformContext.current
        val screenModel = getScreenModel<ImagesScreenModel>()
        val state by screenModel.state.collectAsState()
        LaunchedEffect(true) {
            screenModel.initImages()
        }
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Text(
                    "HD Wallpapers",
                    style = MaterialTheme.typography.caption.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    color = MaterialTheme.colors.onSurface
                )
            }
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (state.loading) {
                    CircularProgressIndicator(Modifier.size(40.dp))
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 80.dp,
                            top = 16.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        state.data?.let { list ->
                            items(list.size) { index ->
                                val request = ImageRequest.Builder(context)
                                    .data(list[index].thumbnail)
                                    .build()
                                AsyncImage(
                                    request,
                                    contentDescription = null,
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier.fillMaxWidth().height(300.dp).clip(
                                        RoundedCornerShape(16.dp)
                                    ).clickable {
                                           changeWallpaper(list[index].url, context)
                                    },
                                    placeholder = painterResource(Res.drawable.landscape_placeholder_svgrepo_com),
                                    error = painterResource(Res.drawable.landscape_placeholder_svgrepo_com),
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}
