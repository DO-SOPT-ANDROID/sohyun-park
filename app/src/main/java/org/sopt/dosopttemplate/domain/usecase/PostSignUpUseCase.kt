package org.sopt.dosopttemplate.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.sopt.dosopttemplate.domain.entity.UserEntity
import org.sopt.dosopttemplate.domain.repository.AuthRepository

class PostSignUpUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(user: UserEntity): Flow<Unit?> = repository.postSignUp(user)
}