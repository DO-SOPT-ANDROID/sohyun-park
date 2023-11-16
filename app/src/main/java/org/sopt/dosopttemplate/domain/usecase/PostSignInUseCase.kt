package org.sopt.dosopttemplate.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.sopt.dosopttemplate.domain.entity.UserEntity
import org.sopt.dosopttemplate.domain.entity.UserIdEntity
import org.sopt.dosopttemplate.domain.repository.AuthRepository

class PostSignInUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(user: UserEntity): Flow<UserIdEntity?> = repository.postSignIn(user)
}