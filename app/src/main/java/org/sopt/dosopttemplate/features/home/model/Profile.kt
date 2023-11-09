package org.sopt.dosopttemplate.features.home.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class Profile : Parcelable {
    abstract val name: String
    abstract val image: String?

    data class My(
        override val image: String?,
        override val name: String
    ) : Profile()

    data class Friend(
        override val image: String?,
        override val name: String,
        val melonMusic: String?,
    ) : Profile()

    data class BirthdayFriend(
        override val image: String?,
        override val name: String,
        val birthday: String
    ) : Profile()
}