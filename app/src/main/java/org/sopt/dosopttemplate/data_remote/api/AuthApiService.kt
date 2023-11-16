package org.sopt.dosopttemplate.data_remote.api

import org.sopt.dosopttemplate.data.dto.request.RequestSignInDto
import org.sopt.dosopttemplate.data.dto.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.dto.response.ResponseSignInDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("api/v1/members")
    suspend fun postSignUp(
        @Body requestSignUp: RequestSignUpDto
    ): Unit

    @POST("api/v1/members/sign-in")
    suspend fun postSignIn(
        @Body requestSignIn: RequestSignInDto
    ): ResponseSignInDto
}