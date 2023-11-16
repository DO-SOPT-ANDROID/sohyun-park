package org.sopt.dosopttemplate.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponseNullable<T>(
    @SerialName("page") val page: Int? = null,
    @SerialName("per_page") val perPage: Int? = null,
    @SerialName("total") val total: Int? = null,
    @SerialName("total_pages") val totalPages: Int? = null,
    @SerialName("data") val data: T? = null,
    @SerialName("support") val support: Support
) {
    @Serializable
    data class Support(
        @SerialName("url") val url: String, @SerialName("text") val text: String
    )
}