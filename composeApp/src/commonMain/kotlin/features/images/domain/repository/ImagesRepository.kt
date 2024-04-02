package features.images.domain.repository

import common.Resource
import features.images.domain.model.ImageModel
import kotlinx.coroutines.flow.Flow

interface ImagesRepository {
    suspend fun getImages(): Flow<Resource<List<ImageModel>>>
}
