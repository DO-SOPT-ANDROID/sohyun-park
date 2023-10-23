package org.sopt.dosopttemplate.features.home.model

sealed class Profile {
    data class My(
        val image: String?,
        val name: String
    ) : Profile()

    data class Friend(
        val image: String?,
        val name: String,
        val melonMusic: String?,
    ) : Profile()

    data class BirthdayFriend(
        val image: String?,
        val name: String,
        val birthday: String
    ) : Profile()
}