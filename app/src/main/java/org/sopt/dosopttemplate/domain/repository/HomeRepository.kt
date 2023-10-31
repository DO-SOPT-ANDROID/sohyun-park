package org.sopt.dosopttemplate.domain.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.dosopttemplate.domain.entity.MockProfileEntity

interface HomeRepository {
    suspend fun getProfile(): Flow<MockProfileEntity>
}