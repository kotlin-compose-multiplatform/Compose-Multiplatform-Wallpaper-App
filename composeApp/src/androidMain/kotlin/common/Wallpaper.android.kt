package common

import android.app.WallpaperManager
import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import com.shageldi.pexels.MainApplication
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL


@OptIn(DelicateCoroutinesApi::class)
actual fun changeWallpaper(url: String, context: Any?) {
  GlobalScope.launch {
      val inputStream = URL(url).openStream()
      WallpaperManager.getInstance((context) as Context).setStream(inputStream)
  }
}
