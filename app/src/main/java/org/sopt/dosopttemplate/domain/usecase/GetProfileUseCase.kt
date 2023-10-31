package org.sopt.dosopttemplate.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.sopt.dosopttemplate.domain.entity.MockProfileEntity
import org.sopt.dosopttemplate.domain.repository.HomeRepository

class GetProfileUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): Flow<MockProfileEntity> = repository.getProfile()
}