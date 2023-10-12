package org.sopt.dosopttemplate.features.account

import java.io.Serializable


data class User(
    val id: String,
    val pw: String,
    val nickname: String,
    val drinkingCapacity: String
) : Serializable
