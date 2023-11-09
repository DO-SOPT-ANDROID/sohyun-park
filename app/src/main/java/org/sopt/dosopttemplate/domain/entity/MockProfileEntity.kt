package org.sopt.dosopttemplate.domain.entity


data class MockProfileEntity(
    val my: Profile.MyProfile,
    val birthdayFriendProfile: List<Profile.BirthdayFriendProfile>,
    val friendProfile: List<Profile.FriendProfile>
)

sealed class Profile {
    abstract val name: String
    data class MyProfile(
        val image: String,
        override val name: String
    ) : Profile()

    data class BirthdayFriendProfile(
        val image: String,
        override val name: String,
        val birthday: String
    ): Profile()

    data class FriendProfile(
        val image: String,
        override val name: String,
        val melonMusic: String
    ): Profile()
}

