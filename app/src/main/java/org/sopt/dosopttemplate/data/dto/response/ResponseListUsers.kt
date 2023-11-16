package org.sopt.dosopttemplate.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.dosopttemplate.domain.entity.ReqresListUsersEntity

@Serializable
data class ResponseListUsers(
    @SerialName("id") val id: Int,
    @SerialName("email") val email: String,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("avatar") val avatar: String
) {
    fun toReqresListUsersEntity() = ReqresListUsersEntity(
        id, email, firstName, lastName, avatar
    )
}