package org.sopt.dosopttemplate.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.core.view.UiState
import org.sopt.dosopttemplate.domain.entity.MockProfileEntity
import org.sopt.dosopttemplate.domain.usecase.GetProfileUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase
) : ViewModel() {
    private val _getProfile = MutableStateFlow<UiState<MockProfileEntity>>(UiState.Loading)
    val getProfile: StateFlow<UiState<MockProfileEntity>> = _getProfile.asStateFlow()

    init {
        getMockProfileList()
    }

    fun getMockProfileList() = viewModelScope.launch {
        getProfileUseCase().collect {
            Timber.d(UiState.Success(it).toString())
            _getProfile.value = UiState.Success(it)
        }
    }
}