package org.sopt.dosopttemplate.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.dosopttemplate.domain.entity.UserIdEntity

@Serializable
data class ResponseSignInDto(
    @SerialName("message") val message: String? = null,
    @SerialName("id") val userId: Int,
    @SerialName("username") val id: String,
    @SerialName("nickname") val nickname: String
) {
    fun toUserIdEntity() = UserIdEntity(
        message, userId
    )
}