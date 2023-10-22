package org.sopt.dosopttemplate.features.account.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.sopt.dosopttemplate.domain.entity.UserEntity


@Parcelize
data class User(
    val id: String,
    val pw: String,
    val nickname: String,
    val drinkingCapacity: String
) : Parcelable {
    fun toUserEntity() = UserEntity(
        id, pw, nickname, drinkingCapacity
    )
}
