package org.sopt.dosopttemplate.features.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.core.view.UiState
import org.sopt.dosopttemplate.features.account.model.User
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
) : ViewModel() {

    private val _signUpValidity = MutableSharedFlow<UiState<User>>()
    val signUpValidity: SharedFlow<UiState<User>> = _signUpValidity.asSharedFlow()

    fun getSignUpValidity(inputSignUpInformation: User) = viewModelScope.launch {
        _signUpValidity.emit(validate(inputSignUpInformation))
    }

    private fun validate(inputSignUpInformation: User): UiState<User> {
        val errorMessage: String? = when {
            !checkIdValidity(inputSignUpInformation.id) -> "아이디 조건은 6~10 글자입니다."
            !checkPwValidity(inputSignUpInformation.pw) -> "비밀번호의 조건은 8~12 글자입니다."
            !checkNicknameValidity(inputSignUpInformation.nickname) -> "닉네임을 입력해주세요."
            !checkDrinkingCapacityValidity(inputSignUpInformation.drinkingCapacity) -> "주량을 입력해주세요."
            else -> null
        }

        return errorMessage?.let { UiState.Failure(it) } ?: UiState.Success(inputSignUpInformation)
    }

    private fun checkIdValidity(id: String) = id.length in MIN_ID_LENGTH..MAX_ID_LENGTH
    private fun checkPwValidity(pw: String) = pw.length in MIN_PW_LENGTH..MAX_PW_LENGTH
    private fun checkNicknameValidity(nickname: String) = nickname.isNotBlank()
    private fun checkDrinkingCapacityValidity(drinkingCapacity: String) =
        drinkingCapacity.isNotBlank()

    companion object {
        const val MIN_ID_LENGTH = 6
        const val MAX_ID_LENGTH = 10
        const val MIN_PW_LENGTH = 8
        const val MAX_PW_LENGTH = 12
    }
}