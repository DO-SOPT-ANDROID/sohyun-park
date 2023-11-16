package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.dto.request.RequestSignInDto
import org.sopt.dosopttemplate.data.dto.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.dto.response.ResponseSignInDto

interface AuthDataSource {
    suspend fun postSignUp(requestSignUpDto: RequestSignUpDto): Unit

    suspend fun postSignIn(requestSignInDto: RequestSignInDto): ResponseSignInDto
}