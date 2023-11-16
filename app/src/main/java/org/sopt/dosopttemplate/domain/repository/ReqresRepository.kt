package org.sopt.dosopttemplate.domain.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.dosopttemplate.domain.entity.ReqresListUsersEntity

interface ReqresRepository {
    suspend fun getListUsers(page: Int): Flow<List<ReqresListUsersEntity>?>
}