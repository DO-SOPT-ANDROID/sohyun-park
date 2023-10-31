package org.sopt.dosopttemplate.domain.entity


data class MockProfileEntity(
    val my: MyProfile,
    val birthdayFriendProfile: List<BirthdayFriendProfile>,
    val friendProfile: List<FriendProfile>
)

data class MyProfile(
    val image: String,
    val name: String
)

data class BirthdayFriendProfile(
    val image: String,
    val name: String,
    val birthday: String
)

data class FriendProfile(
    val image: String,
    val name: String,
    val melonMusic: String
)