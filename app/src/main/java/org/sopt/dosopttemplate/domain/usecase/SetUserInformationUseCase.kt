package org.sopt.dosopttemplate.domain.usecase

import org.sopt.dosopttemplate.domain.entity.UserEntity
import org.sopt.dosopttemplate.domain.repository.AuthRepository

class SetUserInformationUseCase(
    private val authRepository: AuthRepository
) {
    fun setUserInformation(user: UserEntity) = authRepository.setUserInformation(user)
}