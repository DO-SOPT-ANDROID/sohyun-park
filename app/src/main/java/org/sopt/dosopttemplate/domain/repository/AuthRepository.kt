package org.sopt.dosopttemplate.domain.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.dosopttemplate.domain.entity.UserEntity
import org.sopt.dosopttemplate.domain.entity.UserIdEntity

interface AuthRepository {
    fun setUserInformation(userInformation: UserEntity)
    fun getUserInformation(): UserEntity?
    fun setCheckSignIn(checkSignIn: Boolean)
    fun getCheckSignIn(): Boolean
    fun removeUserInformation()
    suspend fun postSignUp(user: UserEntity): Flow<Unit?>

    suspend fun postSignIn(user: UserEntity): Flow<UserIdEntity?>
}