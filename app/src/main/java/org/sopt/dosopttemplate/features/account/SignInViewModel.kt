package org.sopt.dosopttemplate.features.account

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.dosopttemplate.domain.repository.AccountRepository
import org.sopt.dosopttemplate.features.account.model.User
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {
    fun getCheckSignIn(): Boolean = accountRepository.getCheckSignIn()
    fun setCheckSignIn(check: Boolean) = accountRepository.setCheckSignIn(check)
    fun setUserInformation(user: User) = accountRepository.setUserInformation(user.toUserEntity())
    fun getUserInformation(): User? = accountRepository.getUserInformation()?.toUser()
    //회원 탈퇴 구현시 쓰임
    fun removeUserInformation() = accountRepository.removeUserInformation()
}