package org.sopt.dosopttemplate.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.dosopttemplate.features.account.model.User

@Serializable
data class RequestSignUpDto(
    @SerialName("username") val id: String,
    @SerialName("password") val pw: String,
    @SerialName("nickname") val nickname: String,
    @SerialName("drinking_capacity") val drinkingCapacity: String? = null
)