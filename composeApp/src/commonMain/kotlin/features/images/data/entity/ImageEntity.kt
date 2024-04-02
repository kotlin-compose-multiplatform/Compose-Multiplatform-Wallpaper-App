package features.images.data.entity

import features.images.domain.model.ImageModel
import kotlinx.serialization.Serializable

typealias ImageResult = List<ImageEntity>

@Serializable
data class ImageEntity(
    val small: String,
    val large: String,
) {
    fun toUiEntity(): ImageModel {
        return ImageModel(
            id = small,
            url = large,
            thumbnail = small,
            title = "Ok"
        )
    }
}