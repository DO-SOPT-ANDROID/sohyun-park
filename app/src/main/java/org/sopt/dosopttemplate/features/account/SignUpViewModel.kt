package org.sopt.dosopttemplate.features.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.core.view.UiState
import org.sopt.dosopttemplate.domain.entity.UserEntity
import org.sopt.dosopttemplate.domain.usecase.PostSignUpUseCase
import org.sopt.dosopttemplate.domain.usecase.SetUserInformationUseCase
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


    fun getSignUpValidity(inputSignUpInformation: UserEntity) = viewModelScope.launch {
        _signUpValidity.emit(validate(inputSignUpInformation))
    }

    private fun validate(inputSignUpInformation: UserEntity): SignUpState<UserEntity> {
        return when {
            !checkIdValidity(inputSignUpInformation.id) -> SignUpState.Failure(SignUpInputType.ID)
            !checkPwValidity(inputSignUpInformation.pw) -> SignUpState.Failure(SignUpInputType.PW)
            !checkNicknameValidity(inputSignUpInformation.nickname) -> SignUpState.Failure(SignUpInputType.NICK_NAME)
            !checkDrinkingCapacityValidity(inputSignUpInformation.drinkingCapacity) -> SignUpState.Failure(SignUpInputType.DRINKING_CAPACITY)
            else -> SignUpState.Success(inputSignUpInformation)
        }
    }

    private fun checkIdValidity(id: String) = id.length in MIN_ID_LENGTH..MAX_ID_LENGTH
    private fun checkPwValidity(pw: String) = pw.length in MIN_PW_LENGTH..MAX_PW_LENGTH
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
        const val MIN_ID_LENGTH = 6
        const val MAX_ID_LENGTH = 10
        const val MIN_PW_LENGTH = 8
        const val MAX_PW_LENGTH = 12
    }
}