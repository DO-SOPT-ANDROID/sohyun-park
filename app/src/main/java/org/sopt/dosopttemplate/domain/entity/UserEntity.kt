package org.sopt.dosopttemplate.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserEntity(
    val id: String,
    val pw: String,
    val nickname: String,
    val drinkingCapacity: String
) : Parcelable
