package org.sopt.dosopttemplate.features.account

import android.content.Intent
import com.example.core_ui.base.BindingActivity
import com.example.core_ui.context.snackBar
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.core.view.DrinkingCapacityValidityCheck
import org.sopt.dosopttemplate.core.view.IdValidityCheck
import org.sopt.dosopttemplate.core.view.InvalidByBlank
import org.sopt.dosopttemplate.core.view.InvalidByCondition
import org.sopt.dosopttemplate.core.view.NicknameValidityCheck
import org.sopt.dosopttemplate.core.view.PwValidityCheck
import org.sopt.dosopttemplate.core.view.Valid
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.features.util.Account.SIGN_UP_INFORMATION

class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    override fun initView() {
        setClickEventOnSignUpLabelButton()
    }

    private fun setClickEventOnSignUpLabelButton() {
        binding.btnSignUpSignupLabel.setOnClickListener {
            handleSignUpSuccess(saveSignUpInformation())
        }
    }

    private fun saveSignUpInformation(): User {
        with(binding) {
            return User(
                viewSignUpId.etSignUp.text?.toString() ?: " ",
                viewSignUpPw.etSignUp.text?.toString() ?: " ",
                viewSignUpNickname.etSignUp.text?.toString() ?: " ",
                viewSignUpDrinkingCapacity.etSignUp.text?.toString() ?: " "
            )
        }
    }

    private fun checkIdValidity(id: String): Boolean {
        return when (val idState = IdValidityCheck.validate(id)) {
            is Valid -> return true
            is InvalidByBlank -> showSignUpErrorMessage(idState.errorMessage)
            is InvalidByCondition -> showSignUpErrorMessage(idState.errorMessage)
        }
    }

    private fun checkPwValidity(pw: String): Boolean {
        return when (val pwState = PwValidityCheck.validate(pw)) {
            is Valid -> return true
            is InvalidByBlank -> showSignUpErrorMessage(pwState.errorMessage)
            is InvalidByCondition -> showSignUpErrorMessage(pwState.errorMessage)
        }
    }

    private fun checkNicknameValidity(nickname: String): Boolean {
        return when (val nicknameState = NicknameValidityCheck.validate(nickname)) {
            is Valid -> return true
            is InvalidByBlank -> showSignUpErrorMessage(nicknameState.errorMessage)
            is InvalidByCondition -> showSignUpErrorMessage(nicknameState.errorMessage)
        }
    }

    private fun checkDrinkingCapacityValidity(drinkingCapacity: String): Boolean {
        return when (val drinkingCapacityState =
            DrinkingCapacityValidityCheck.validate(drinkingCapacity)) {
            is Valid -> return true
            is InvalidByBlank -> showSignUpErrorMessage(drinkingCapacityState.errorMessage)
            is InvalidByCondition -> showSignUpErrorMessage(drinkingCapacityState.errorMessage)
        }
    }

    private fun handleSignUpSuccess(inputInformation: User) {
        with(inputInformation) {
            if (checkIdValidity(id) && checkPwValidity(pw) && checkNicknameValidity(nickname) && checkDrinkingCapacityValidity(
                    drinkingCapacity
                )
            ) {
                val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                intent.putExtra(SIGN_UP_INFORMATION, this)
                setResult(RESULT_OK, intent)
                finish()
            }
        }

    }

    private fun showSignUpErrorMessage(errorMessage: String): Boolean {
        snackBar(binding.root) { errorMessage }
        return false
    }
}