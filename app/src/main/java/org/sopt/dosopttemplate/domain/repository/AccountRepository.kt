package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.domain.entity.User

interface AccountRepository {
    fun setUserInformation(userInformation: User)
    fun getUserInformation(): User
    fun setCheckSignIn(checkSignIn: Boolean)
    fun getCheckSignIn(): Boolean
}