package features.images.presentation.state

import features.images.domain.model.ImageModel

data class ImagesState(
    val loading: Boolean = true,
    val error: Boolean = false,
    val data: List<ImageModel>? = emptyList()
)