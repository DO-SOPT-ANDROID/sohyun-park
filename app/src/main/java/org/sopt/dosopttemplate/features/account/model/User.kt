package org.sopt.dosopttemplate.features.account.model

import java.io.Serializable


data class User(
    val id: String,
    val pw: String,
    val nickname: String,
    val drinkingCapacity: String
) : Serializable
