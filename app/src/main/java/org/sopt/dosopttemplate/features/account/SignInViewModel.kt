package org.sopt.dosopttemplate.features.account

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.dosopttemplate.domain.repository.AuthRepository
import org.sopt.dosopttemplate.features.account.model.User
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    fun getCheckSignIn(): Boolean = authRepository.getCheckSignIn()
    fun setCheckSignIn(check: Boolean) = authRepository.setCheckSignIn(check)
    fun setUserInformation(user: User) = authRepository.setUserInformation(user.toUserEntity())
    fun getUserInformation(): User? = authRepository.getUserInformation()?.toUser()

    //회원 탈퇴 구현시 쓰임
    fun removeUserInformation() = authRepository.removeUserInformation()
}