package org.sopt.dosopttemplate.features.doandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import org.sopt.dosopttemplate.features.home.model.Profile
import org.sopt.dosopttemplate.features.util.Event
import javax.inject.Inject

@HiltViewModel
class DoAndroidViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase
) : ViewModel() {
    private val _getProfile = MutableStateFlow<UiState<MockProfileEntity>>(UiState.Loading)
    val getProfile: StateFlow<UiState<MockProfileEntity>> = _getProfile.asStateFlow()

    private val _openProfileDetail = MutableLiveData<Event<Profile>>()
    val openProfileDetail: LiveData<Event<Profile>> = _openProfileDetail
    init {
        getMockProfileList()
    }
    private fun getMockProfileList() = viewModelScope.launch {
        getProfileUseCase().collect {
            _getProfile.value = UiState.Success(it)
        }
    }

    fun openProfileDetail(profileInfo: Profile) {
        _openProfileDetail.value = Event(profileInfo)
    }
}