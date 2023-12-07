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

    private val _signUpValidity = MutableSharedFlow<SignUpState<UserEntity>>()
    val signUpValidity: SharedFlow<SignUpState<UserEntity>> = _signUpValidity.asSharedFlow()

    private val _postSignUp = MutableSharedFlow<UiState<Unit?>>()
    val postSignUp: SharedFlow<UiState<Unit?>> = _postSignUp.asSharedFlow()

    val id = MutableLiveData("")

    val pw = MutableLiveData("")

    val nickname = MutableLiveData("")

    val drinkingCapacity = MutableLiveData("")

    private val _isIdValid = id.map { id -> checkIdValidity(id) }
    val isIdValid: LiveData<Boolean> get() = _isIdValid

    private val _isPwValid = pw.map { pw -> checkPwValidity(pw) }
    val isPwValid: LiveData<Boolean> get() = _isPwValid

    private val _isNicknameValid = nickname.map { nickname -> checkNicknameValidity(nickname) }
    val isNicknameValid: LiveData<Boolean> get() = _isNicknameValid

    private val _isDrinkingCapacityValid =
        drinkingCapacity.map { drinkingCapacity -> checkDrinkingCapacityValidity(drinkingCapacity) }
    val isDrinkingCapacityValid: LiveData<Boolean> get() = _isDrinkingCapacityValid


    fun getSignUpValidity(inputSignUpInformation: UserEntity) = viewModelScope.launch {
        _signUpValidity.emit(validate(inputSignUpInformation))
    }

    private fun validate(inputSignUpInformation: UserEntity): SignUpState<UserEntity> {
        return when {
            !checkIdValidity(inputSignUpInformation.id) -> SignUpState.Failure(SignUpInputType.ID)
            !checkPwValidity(inputSignUpInformation.pw) -> SignUpState.Failure(SignUpInputType.PW)
            !checkNicknameValidity(inputSignUpInformation.nickname) -> SignUpState.Failure(
                SignUpInputType.NICK_NAME
            )

            !checkDrinkingCapacityValidity(inputSignUpInformation.drinkingCapacity) -> SignUpState.Failure(
                SignUpInputType.DRINKING_CAPACITY
            )

            else -> SignUpState.Success(inputSignUpInformation)
        }
    }

    private fun checkIdValidity(id: String) = Pattern.matches(REGEX_ID_PATTERN, id)
    private fun checkPwValidity(pw: String) = Pattern.matches(REGEX_PW_PATTERN, pw)
    private fun checkNicknameValidity(nickname: String) = nickname.isNotBlank()
    private fun checkDrinkingCapacityValidity(drinkingCapacity: String) =
        Pattern.matches(REGEX_DRINKING_CAPACITY_PATTERN, drinkingCapacity)

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
        const val REGEX_DRINKING_CAPACITY_PATTERN = "\\d+병"
    }
}