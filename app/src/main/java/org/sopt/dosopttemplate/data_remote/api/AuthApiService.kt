package org.sopt.dosopttemplate.data_remote.api

import org.sopt.dosopttemplate.data.dto.BaseResponseNullable
import org.sopt.dosopttemplate.data.dto.request.RequestSignUpDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApiService {

    @POST("api/v1/members")
    suspend fun postSignUp(
        @Body request: RequestSignUpDto
    ): Unit
}