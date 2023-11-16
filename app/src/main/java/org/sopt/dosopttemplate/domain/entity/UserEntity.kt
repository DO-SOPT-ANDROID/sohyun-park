package org.sopt.dosopttemplate.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.sopt.dosopttemplate.features.account.model.User


@Parcelize
data class UserEntity(
    val id: String,
    val pw: String,
    val nickname: String = "",
    val drinkingCapacity: String = ""
) : Parcelable {
    fun toUser() = User(
        id, pw, nickname, drinkingCapacity
    )
}
