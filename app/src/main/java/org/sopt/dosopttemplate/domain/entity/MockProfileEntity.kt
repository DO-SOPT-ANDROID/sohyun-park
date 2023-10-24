package org.sopt.dosopttemplate.domain.entity



data class MockProfileEntity(
    val my: MyProfile,
    val birthdayFriendProfile: List<FriendProfile>,
    val friendProfile: List<FriendProfile>
)

data class MyProfile(
    val image: String,
    val name: String
)

data class FriendProfile(
    val image: String,
    val name: String,
    val birthday: String,
    val melonMusic: String
)