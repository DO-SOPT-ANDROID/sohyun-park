package org.sopt.dosopttemplate.data.repositoryimpl

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.sopt.dosopttemplate.data.datasource.ReqresDataSource
import org.sopt.dosopttemplate.domain.entity.ReqresListUsersEntity
import org.sopt.dosopttemplate.domain.repository.ReqresRepository
import javax.inject.Inject

class ReqresRepositoryImpl @Inject constructor(
    private val reqresDataSource: ReqresDataSource
) : ReqresRepository {
    override suspend fun getListUsers(page: Int): Flow<List<ReqresListUsersEntity>?> {
        return flow {
            val result = runCatching {
                reqresDataSource.getListUsers(page).data?.map { it.toReqresListUsersEntity() }
            }
            emit(result.getOrDefault(emptyList()))
        }
    }
}