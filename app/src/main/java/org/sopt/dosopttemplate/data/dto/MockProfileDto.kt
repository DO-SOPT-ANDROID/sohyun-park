package org.sopt.dosopttemplate.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.dosopttemplate.domain.entity.MockProfileEntity
import org.sopt.dosopttemplate.domain.entity.Profile

@Serializable
data class MockProfileDto(
    @SerialName("my") val my: MyProfileDto,
    @SerialName("birthday_friend") val birthdayFriendProfile: List<BirthdayFriendProfileDto>,
    @SerialName("friend") val friendProfile: List<FriendProfileDto>
) {
    fun toMockProfileEntity(): MockProfileEntity {
        val myProfileEntity = my.toMyProfileEntity()
        val birthdayFriendProfileEntities =
            birthdayFriendProfile.map { it.toBirthdayFriendProfileEntity() }
        val friendProfileEntities = friendProfile.map { it.toFriendProfileEntity() }
        return MockProfileEntity(
            myProfileEntity, birthdayFriendProfileEntities, friendProfileEntities
        )
    }
}

@Serializable
data class MyProfileDto(
    @SerialName("image") val image: String, @SerialName("name") val name: String
) {
    fun toMyProfileEntity() = Profile.MyProfile(image, name)
}

@Serializable
data class BirthdayFriendProfileDto(
    @SerialName("image") val image: String,
    @SerialName("name") val name: String,
    @SerialName("birthday") val birthday: String
) {
    fun toBirthdayFriendProfileEntity() = Profile.BirthdayFriendProfile(image, name, birthday)
}

@Serializable
data class FriendProfileDto(
    @SerialName("image") val image: String,
    @SerialName("name") val name: String,
    @SerialName("melon_music") val melonMusic: String
) {
    fun toFriendProfileEntity() = Profile.FriendProfile(image, name, melonMusic)
}

