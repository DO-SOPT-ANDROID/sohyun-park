package org.sopt.dosopttemplate.features.account

sealed class SignUpState<out T> {
    object Loading : SignUpState<Nothing>()

    data class Success<T>(val data: T) : SignUpState<T>()

    data class Failure(val type: SignUpInputType) : SignUpState<Nothing>()
}

enum class SignUpInputType {
    ID,
    PW,
    NICK_NAME,
    DRINKING_CAPACITY
}