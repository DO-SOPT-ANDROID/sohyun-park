package org.sopt.dosopttemplate.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.sopt.dosopttemplate.domain.entity.ReqresListUsersEntity
import org.sopt.dosopttemplate.domain.repository.ReqresRepository

class GetListUsersUseCase(
    private val reqresRepository: ReqresRepository
) {
    suspend operator fun invoke(page: Int): Flow<List<ReqresListUsersEntity>?> =
        reqresRepository.getListUsers(page)
}