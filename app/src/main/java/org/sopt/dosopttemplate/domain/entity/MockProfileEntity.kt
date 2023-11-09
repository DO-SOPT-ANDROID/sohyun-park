package org.sopt.dosopttemplate.domain.entity


data class MockProfileEntity(
    val my: Profile.MyProfile,
    val birthdayFriendProfile: List<Profile.BirthdayFriendProfile>,
    val friendProfile: List<Profile.FriendProfile>
)

sealed class Profile {
    abstract val name: String
    abstract val image: String

    data class MyProfile(
        override val image: String,
        override val name: String
    ) : Profile()

    data class BirthdayFriendProfile(
        override val image: String,
        override val name: String,
        val birthday: String
    ) : Profile()

    data class FriendProfile(
        override val image: String,
        override val name: String,
        val melonMusic: String
    ) : Profile()
}

