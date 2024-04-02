package features.images.data.api

import common.url
import features.images.data.entity.ImageEntity
import features.images.data.entity.ImageResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class ImagesApi(
    private val httpClient: HttpClient
) {
    suspend fun getImages(): ImageResult {
        return httpClient.get(url).body()
    }
}
