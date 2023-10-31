package org.sopt.dosopttemplate.features.home.mapper

import org.sopt.dosopttemplate.domain.entity.MockProfileEntity
import org.sopt.dosopttemplate.features.home.model.Profile

fun MockProfileEntity.toProfileList(): List<Profile> {
    val myProfile = Profile.My(my.image, my.name)
    val birthdayFriends = birthdayFriendProfile.map {
        Profile.BirthdayFriend(it.image, it.name, it.birthday)
    }
    val friends = friendProfile.map {
        Profile.Friend(it.image, it.name, it.melonMusic)
    }

    val profileList = mutableListOf<Profile>()
    profileList.add(myProfile)
    profileList.addAll(birthdayFriends)
    profileList.addAll(friends)

    return profileList
}