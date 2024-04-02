package features.images.data.repository

import common.Resource
import features.images.data.api.ImagesApi
import features.images.domain.model.ImageModel
import features.images.domain.repository.ImagesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ImagesRepositoryImple(
    private val api: ImagesApi
) : ImagesRepository {
    override suspend fun getImages(): Flow<Resource<List<ImageModel>>> = flow {
        emit(Resource.Loading())
        try {
            val result = api.getImages()
            emit(Resource.Success(result.map { it.toUiEntity() }))
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }

}