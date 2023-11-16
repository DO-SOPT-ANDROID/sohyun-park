package org.sopt.dosopttemplate.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.core.view.UiState
import org.sopt.dosopttemplate.domain.entity.MockProfileEntity
import org.sopt.dosopttemplate.domain.entity.ReqresListUsersEntity
import org.sopt.dosopttemplate.domain.usecase.GetListUsersUseCase
import org.sopt.dosopttemplate.domain.usecase.GetProfileUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val getListUsersUseCase: GetListUsersUseCase
) : ViewModel() {
    private val _getProfile = MutableStateFlow<UiState<MockProfileEntity>>(UiState.Loading)
    val getProfile: StateFlow<UiState<MockProfileEntity>> = _getProfile.asStateFlow()

    private val _getReqresListUsers =
        MutableStateFlow<UiState<List<ReqresListUsersEntity>?>>(UiState.Loading)
    val getReqresListUsers: StateFlow<UiState<List<ReqresListUsersEntity>?>> = _getReqresListUsers

    init {
        getMockProfileList()
        getReqresListUsers(1)
    }

    private fun getMockProfileList() = viewModelScope.launch {
        getProfileUseCase().collect {
            _getProfile.value = UiState.Success(it)
        }
    }

    private fun getReqresListUsers(page: Int) = viewModelScope.launch {
        getListUsersUseCase(page).collectLatest {
            _getReqresListUsers.value = UiState.Success(it)
        }
    }
}