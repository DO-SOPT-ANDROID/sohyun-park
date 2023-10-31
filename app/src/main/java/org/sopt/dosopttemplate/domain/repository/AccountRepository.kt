package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.domain.entity.UserEntity

interface AccountRepository {
    fun setUserInformation(userInformation: UserEntity)
    fun getUserInformation(): UserEntity?
    fun setCheckSignIn(checkSignIn: Boolean)
    fun getCheckSignIn(): Boolean
    fun removeUserInformation()
}