package org.sopt.dosopttemplate.core.view

sealed interface RegistrationState

data class Valid(val valid: Boolean) : RegistrationState
data class InvalidByBlank(val errorMessage: String) : RegistrationState
data class InvalidByCondition(val errorMessage: String) : RegistrationState

sealed interface RegistrationCheck {
    fun validate(input: String): RegistrationState
}

object IdValidityCheck : RegistrationCheck {
    override fun validate(input: String): RegistrationState {
        return when {
            input.length in 6..10 -> Valid(true)
            input.isBlank() -> InvalidByBlank("아이디를 입력해주세요.")
            else -> InvalidByCondition("아이디 조건은 6~10 글자입니다.")
        }
    }
}

object PwValidityCheck : RegistrationCheck {
    override fun validate(input: String): RegistrationState {
        return when {
            input.length in 8..12 -> Valid(true)
            input.isBlank() -> InvalidByBlank("비밀번호를 입력해주세요.")
            else -> InvalidByCondition("비밀번호의 조건은 8~12 글자입니다.")
        }
    }
}

object NicknameValidityCheck : RegistrationCheck {
    override fun validate(input: String): RegistrationState {
        return when {
            input.isNotBlank() -> Valid(true)
            else -> InvalidByCondition("닉네임을 입력해주세요.")
        }
    }
}

object DrinkingCapacityValidityCheck : RegistrationCheck {
    override fun validate(input: String): RegistrationState {
        return when {
            input.isNotBlank() -> Valid(true)
            else -> InvalidByCondition("주량을 입력해주세요.")
        }
    }
}