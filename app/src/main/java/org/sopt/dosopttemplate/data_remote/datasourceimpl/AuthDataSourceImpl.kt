package org.sopt.dosopttemplate.data_remote.datasourceimpl

import org.sopt.dosopttemplate.data.datasource.AuthDataSource
import org.sopt.dosopttemplate.data.dto.request.RequestSignInDto
import org.sopt.dosopttemplate.data.dto.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.dto.response.ResponseSignInDto
import org.sopt.dosopttemplate.data_remote.api.AuthApiService
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authApiService: AuthApiService
) : AuthDataSource {
    override suspend fun postSignUp(requestSignUpDto: RequestSignUpDto) {
        return authApiService.postSignUp(requestSignUpDto)
    }

    override suspend fun postSignIn(requestSignInDto: RequestSignInDto): ResponseSignInDto {
        return authApiService.postSignIn(requestSignInDto)
    }
}