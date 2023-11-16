package org.sopt.dosopttemplate.data.repositoryimpl

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.sopt.dosopttemplate.data.datasource.AuthDataSource
import org.sopt.dosopttemplate.data.datasource.SharedPreferenceDataSource
import org.sopt.dosopttemplate.data.dto.request.RequestSignUpDto
import org.sopt.dosopttemplate.domain.entity.UserEntity
import org.sopt.dosopttemplate.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val sharedPreferencesDataSource: SharedPreferenceDataSource,
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override fun setUserInformation(userInformation: UserEntity) {
        sharedPreferencesDataSource.userInformation = userInformation
    }

    override fun getUserInformation(): UserEntity? {
        return sharedPreferencesDataSource.userInformation
    }

    override fun setCheckSignIn(checkSignIn: Boolean) {
        sharedPreferencesDataSource.checkSignIn = checkSignIn
    }

    override fun getCheckSignIn(): Boolean {
        return sharedPreferencesDataSource.checkSignIn
    }

    override fun removeUserInformation() {
        sharedPreferencesDataSource.removeUserInformation()
    }

    override suspend fun postSignUp(user: UserEntity): Flow<Unit?> {
        return flow {
            val result = kotlin.runCatching {
                authDataSource.postSignUp(RequestSignUpDto(user.id, user.pw, user.nickname))
            }
            Log.d("ttt", result.toString())
            emit(result.getOrNull())
        }
    }
}