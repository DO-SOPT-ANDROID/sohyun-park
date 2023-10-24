package org.sopt.dosopttemplate.data.repositoryimpl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.sopt.dosopttemplate.data.datasource.HomeDataSource
import org.sopt.dosopttemplate.domain.entity.MockProfileEntity
import org.sopt.dosopttemplate.domain.entity.MyProfile
import org.sopt.dosopttemplate.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: HomeDataSource
) : HomeRepository {
    override suspend fun getProfile(): Flow<MockProfileEntity> {
        return flow {
            val result = kotlin.runCatching {
                dataSource.getProfile().toMockProfileEntity()
            }
            emit(
                result.getOrDefault(
                    MockProfileEntity(
                        MyProfile("", ""), emptyList(), emptyList()
                    )
                )
            )
        }
    }
}