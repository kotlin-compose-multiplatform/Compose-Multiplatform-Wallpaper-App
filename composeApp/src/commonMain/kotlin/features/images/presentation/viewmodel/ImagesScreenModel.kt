package features.images.presentation.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import common.Resource
import features.images.domain.use_case.ImagesUseCase
import features.images.presentation.state.ImagesState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ImagesScreenModel(
    private val useCase: ImagesUseCase
) : StateScreenModel<ImagesState>(ImagesState()) {
    fun getImages() {
        screenModelScope.launch {
            useCase().onEach {
                when (it) {
                    is Resource.Loading -> {
                        mutableState.value = mutableState.value.copy(
                            loading = true,
                            error = false,
                            data = it.data
                        )
                    }

                    is Resource.Error -> {
                        mutableState.value = mutableState.value.copy(
                            loading = false,
                            error = true,
                            data = it.data
                        )
                    }

                    is Resource.Success -> {
                        mutableState.value = mutableState.value.copy(
                            loading = false,
                            error = false,
                            data = it.data
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    fun initImages() {
        if(mutableState.value.data.isNullOrEmpty()) {
            getImages()
        }
    }
}