package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.dto.request.RequestSignUpDto

interface AuthDataSource {
    suspend fun postSignUp(requestSignUpDto: RequestSignUpDto): Unit
}