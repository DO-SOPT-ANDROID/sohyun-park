package org.sopt.dosopttemplate.features.home.model

sealed class Profile {
    abstract val name: String

    data class My(
        val image: String?,
        override val name: String
    ) : Profile()

    data class Friend(
        val image: String?,
        override val name: String,
        val melonMusic: String?,
    ) : Profile()

    data class BirthdayFriend(
        val image: String?,
        override val name: String,
        val birthday: String
    ) : Profile()
}