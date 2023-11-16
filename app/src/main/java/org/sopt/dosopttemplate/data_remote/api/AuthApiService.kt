package org.sopt.dosopttemplate.data_remote.api

import org.sopt.dosopttemplate.data.dto.request.RequestSignInDto
import org.sopt.dosopttemplate.data.dto.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.dto.response.ResponseSignInDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    companion object {
        const val API = "api"
        const val V1 = "v1"
        const val MEMBERS = "members"
        const val SIGN_IN = "sign-in"
    }

    @POST("$API/$V1/$MEMBERS")
    suspend fun postSignUp(
        @Body requestSignUp: RequestSignUpDto
    )

    @POST("$API/$V1/$MEMBERS/$SIGN_IN")
    suspend fun postSignIn(
        @Body requestSignIn: RequestSignInDto
    ): ResponseSignInDto
}