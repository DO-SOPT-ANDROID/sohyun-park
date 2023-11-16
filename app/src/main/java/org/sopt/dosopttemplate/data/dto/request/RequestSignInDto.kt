package org.sopt.dosopttemplate.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignInDto(
    @SerialName("username") val id: String,
    @SerialName("password") val pw: String
)
