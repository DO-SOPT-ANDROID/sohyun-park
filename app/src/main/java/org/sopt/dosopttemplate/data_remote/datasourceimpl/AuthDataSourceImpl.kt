package org.sopt.dosopttemplate.data_remote.datasourceimpl

import org.sopt.dosopttemplate.data.datasource.AuthDataSource
import org.sopt.dosopttemplate.data.dto.request.RequestSignUpDto
import org.sopt.dosopttemplate.data_remote.api.AuthApiService
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authApiService: AuthApiService
) : AuthDataSource {
    override suspend fun postSignUp(requestSignUpDto: RequestSignUpDto): Unit {
        return authApiService.postSignUp(requestSignUpDto)
    }
}