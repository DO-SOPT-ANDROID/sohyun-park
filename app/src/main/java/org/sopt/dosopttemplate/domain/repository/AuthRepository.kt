package org.sopt.dosopttemplate.domain.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.dosopttemplate.data.dto.BaseResponseNullable
import org.sopt.dosopttemplate.domain.entity.UserEntity

interface AuthRepository {
    fun setUserInformation(userInformation: UserEntity)
    fun getUserInformation(): UserEntity?
    fun setCheckSignIn(checkSignIn: Boolean)
    fun getCheckSignIn(): Boolean
    fun removeUserInformation()
    suspend fun postSignUp(user: UserEntity): Flow<Unit?>
}