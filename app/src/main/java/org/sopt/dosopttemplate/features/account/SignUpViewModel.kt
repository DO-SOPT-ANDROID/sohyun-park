package org.sopt.dosopttemplate.features.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.core.view.UiState
import org.sopt.dosopttemplate.domain.entity.UserEntity
import org.sopt.dosopttemplate.domain.usecase.PostSignUpUseCase
import org.sopt.dosopttemplate.domain.usecase.SetUserInformationUseCase
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val setUserInformationUseCase: SetUserInformationUseCase,
    private val postSignUpUseCase: PostSignUpUseCase
) : ViewModel() {

    private val _signUpValidity = MutableSharedFlow<UiState<UserEntity>>()
    val signUpValidity: SharedFlow<UiState<UserEntity>> = _signUpValidity.asSharedFlow()

    private val _postSignUp = MutableSharedFlow<UiState<Unit?>>()
    val postSignUp: SharedFlow<UiState<Unit?>> = _postSignUp.asSharedFlow()

    val _id = MutableLiveData("")

    val _pw = MutableLiveData("")

    val _nickname = MutableLiveData("")


    val isIdValid: LiveData<Boolean> = _id.map { id -> checkIdValidity(id) || id.isBlank() }

    val isPwValid: LiveData<Boolean> = _pw.map { pw -> checkPwValidity(pw) || pw.isBlank() }

    val isNicknameValid: LiveData<Boolean> =
        _pw.map { nickname -> checkNicknameValidity(nickname) || nickname.isBlank() }


    fun getSignUpValidity(inputSignUpInformation: UserEntity) = viewModelScope.launch {
        _signUpValidity.emit(validate(inputSignUpInformation))
    }

    private fun validate(inputSignUpInformation: UserEntity): UiState<UserEntity> {
        val errorMessage: String? = when {
            !checkIdValidity(inputSignUpInformation.id) -> "아이디 조건은 6~10 글자입니다."
            !checkPwValidity(inputSignUpInformation.pw) -> "비밀번호의 조건은 8~12 글자입니다."
            !checkNicknameValidity(inputSignUpInformation.nickname) -> "닉네임을 입력해주세요."
            !checkDrinkingCapacityValidity(inputSignUpInformation.drinkingCapacity) -> "주량을 입력해주세요."
            else -> null
        }

        return errorMessage?.let { UiState.Failure(it) } ?: UiState.Success(inputSignUpInformation)
    }

    private fun checkIdValidity(id: String) = Pattern.matches(REGEX_ID_PATTERN, id)
    private fun checkPwValidity(pw: String) = Pattern.matches(REGEX_PW_PATTERN, pw)
    private fun checkNicknameValidity(nickname: String) = nickname.isNotBlank()
    private fun checkDrinkingCapacityValidity(drinkingCapacity: String) =
        drinkingCapacity.isNotBlank()

    fun setUserInformation(user: UserEntity) = setUserInformationUseCase.setUserInformation(user)

    fun postSignUp(inputSignUpInformation: UserEntity) = viewModelScope.launch {
        postSignUpUseCase(inputSignUpInformation).collect {
            _postSignUp.emit(UiState.Success(it))
        }
    }


    companion object {
        private const val MIN_ID_LENGTH = 6
        private const val MAX_ID_LENGTH = 10
        private const val MIN_PW_LENGTH = 6
        private const val MAX_PW_LENGTH = 12
        const val REGEX_ID_PATTERN =
            "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{$MIN_ID_LENGTH,$MAX_ID_LENGTH}\$"
        const val REGEX_PW_PATTERN =
            "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[^\\w\\s])[a-zA-Z\\d\\S]{$MIN_PW_LENGTH,$MAX_PW_LENGTH}\$"

    }
}