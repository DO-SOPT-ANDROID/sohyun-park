package org.sopt.dosopttemplate.features.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.core.view.UiState
import org.sopt.dosopttemplate.domain.entity.UserEntity
import org.sopt.dosopttemplate.domain.entity.UserIdEntity
import org.sopt.dosopttemplate.domain.repository.AuthRepository
import org.sopt.dosopttemplate.domain.usecase.PostSignInUseCase
import org.sopt.dosopttemplate.features.account.model.User
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val postSignInUseCase: PostSignInUseCase
) : ViewModel() {

    private val _postSignIn = MutableSharedFlow<UiState<UserIdEntity?>>()
    val postSignIn: SharedFlow<UiState<UserIdEntity?>> = _postSignIn.asSharedFlow()

    fun getCheckSignIn(): Boolean = authRepository.getCheckSignIn()
    fun setCheckSignIn(check: Boolean) = authRepository.setCheckSignIn(check)
    fun setUserInformation(user: User) = authRepository.setUserInformation(user.toUserEntity())
    fun getUserInformation(): User? = authRepository.getUserInformation()?.toUser()

    //회원 탈퇴 구현시 쓰임
    fun removeUserInformation() = authRepository.removeUserInformation()

    fun postSignIn(inputSignInInformation: UserEntity) = viewModelScope.launch {
        postSignInUseCase(inputSignInInformation).collect {
            _postSignIn.emit(UiState.Success(it))
        }
    }
}