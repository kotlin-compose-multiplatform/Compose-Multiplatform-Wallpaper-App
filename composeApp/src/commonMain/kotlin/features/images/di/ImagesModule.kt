package features.images.di

import common.httpClient
import features.images.data.api.ImagesApi
import features.images.domain.use_case.ImagesUseCase
import features.images.presentation.viewmodel.ImagesScreenModel
import features.images.domain.repository.ImagesRepository
import features.images.data.repository.ImagesRepositoryImple
import org.koin.dsl.module

val imagesModule = module {
    single(createdAtStart = true) {
        ImagesApi(httpClient = httpClient)
    }
    single<ImagesRepository>(createdAtStart = true) {
        ImagesRepositoryImple(api = get())
    }
    single(createdAtStart = true) {
        ImagesUseCase(imagesRepository = get())
    }
    factory { ImagesScreenModel(useCase = get()) }
}