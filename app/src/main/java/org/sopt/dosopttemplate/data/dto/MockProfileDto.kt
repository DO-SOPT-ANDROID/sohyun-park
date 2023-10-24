package org.sopt.dosopttemplate.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.dosopttemplate.domain.entity.FriendProfile
import org.sopt.dosopttemplate.domain.entity.MockProfileEntity
import org.sopt.dosopttemplate.domain.entity.MyProfile

@Serializable
data class MockProfileDto(
    @SerialName("my")
    val my: MyProfileDto,
    @SerialName("birthday_friend")
    val birthdayFriendProfile: List<FriendProfileDto>,
    @SerialName("friend")
    val friendProfile: List<FriendProfileDto>
) {
    fun toMockProfileEntity(): MockProfileEntity {
        val myProfileEntity = MyProfile(my.image, my.name)
        val friendProfileEntities = birthdayFriendProfile.map { it.toFriendProfileEntity() }
        return MockProfileEntity(myProfileEntity, friendProfileEntities, friendProfileEntities)
    }
}

@Serializable
data class MyProfileDto(
    @SerialName("image")
    val image: String,
    @SerialName("name")
    val name: String
) {
    fun toMyProfileEntity() = MyProfile(
        image, name
    )
}

@Serializable
data class FriendProfileDto(
    @SerialName("image")
    val image: String,
    @SerialName("name")
    val name: String,
    @SerialName("birthday")
    val birthday: String,
    @SerialName("melon_music")
    val melonMusic: String
) {
    fun toFriendProfileEntity() = FriendProfile(
        image, name, birthday, melonMusic
    )
}

