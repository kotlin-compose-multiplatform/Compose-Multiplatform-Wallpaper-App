package features.images.domain.use_case

import common.Resource
import features.images.domain.model.ImageModel
import features.images.domain.repository.ImagesRepository
import kotlinx.coroutines.flow.Flow

class ImagesUseCase(
    private val imagesRepository: ImagesRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<ImageModel>>> {
        return imagesRepository.getImages()
    }
}