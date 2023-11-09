package org.sopt.dosopttemplate.data.repositoryimpl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.sopt.dosopttemplate.data.datasource.HomeDataSource
import org.sopt.dosopttemplate.domain.entity.MockProfileEntity
import org.sopt.dosopttemplate.domain.entity.Profile
import org.sopt.dosopttemplate.domain.repository.HomeRepository
import timber.log.Timber
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: HomeDataSource
) : HomeRepository {
    override suspend fun getProfile(): Flow<MockProfileEntity> {
        return flow {
            val result = runCatching {
                dataSource.getProfile().toMockProfileEntity()
            }
            if(result.isFailure){
                Timber.e(result.exceptionOrNull(),"error code")
            }
            emit(
                result.getOrDefault(
                    MockProfileEntity(
                        Profile.MyProfile("test", "test"), emptyList(), emptyList()
                    )
                )
            )
        }
    }
}