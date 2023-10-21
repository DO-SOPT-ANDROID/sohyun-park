package org.sopt.dosopttemplate.data.repositoryimpl

import org.sopt.dosopttemplate.data_local.datasourceimpl.SharedPreferencesDataSourceImpl
import org.sopt.dosopttemplate.domain.entity.User
import org.sopt.dosopttemplate.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val sharedPreferencesDataSourceImpl: SharedPreferencesDataSourceImpl
) : AccountRepository {
    override fun setUserInformation(userInformation: User) {
        sharedPreferencesDataSourceImpl.userInformation = userInformation
    }

    override fun getUserInformation(): User {
        return sharedPreferencesDataSourceImpl.userInformation
    }

    override fun setCheckSignIn(checkSignIn: Boolean) {
        sharedPreferencesDataSourceImpl.checkSignIn = checkSignIn
    }

    override fun getCheckSignIn(): Boolean {
        return sharedPreferencesDataSourceImpl.checkSignIn
    }
}