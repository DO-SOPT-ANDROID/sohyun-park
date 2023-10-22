package org.sopt.dosopttemplate.features.account

import android.content.Intent
import androidx.activity.viewModels
import com.example.core_ui.base.BindingActivity
import org.sopt.dosopttemplate.core.context.snackBar
import org.sopt.dosopttemplate.core.context.toast
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.core.context.navigateTo
import org.sopt.dosopttemplate.databinding.ActivitySignInBinding
import org.sopt.dosopttemplate.features.account.model.User
import org.sopt.dosopttemplate.features.main.MainActivity

@AndroidEntryPoint
class SignInActivity : BindingActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private lateinit var savedSignUpInformation: User
    private val viewModel by viewModels<SignInViewModel>()

    override fun initView() {
        setUserInformation()
        setClickEventOnSignUpLabelButton()
        setClickEventOnSignInLabelButton()
    }

    private fun setUserInformation() {
        viewModel.getUserInformation()?.let { savedSignUpInformation = it }
    }

    private fun setClickEventOnSignUpLabelButton() {
        binding.btnSignInSignupLabel.setOnClickListener {
            navigateToSignUp()
        }
    }

    private fun navigateToSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun setClickEventOnSignInLabelButton() {
        binding.btnSignInSigninLabel.setOnClickListener {
            handleSignIn()
        }
    }

    private fun isSignInSuccessful(user: User): Boolean {
        return checkIdIdentification(user.id) && checkPwIdentification(user.pw)
    }

    private fun checkIdIdentification(id: String): Boolean {
        return binding.etSignInId.text.toString() == id
    }

    private fun checkPwIdentification(pw: String): Boolean {
        return binding.etSignInPw.text.toString() == pw
    }

    private fun handleSignInSuccess() {
        viewModel.setCheckSignIn(true)
        toast(getString(R.string.error_message_valid_sign_in))
        navigateToMain()
    }

    private fun handleSignIn() {
        if (this::savedSignUpInformation.isInitialized) {
            if (isSignInSuccessful(savedSignUpInformation)) handleSignInSuccess() else showSignInErrorMessage()
        } else {
            showSignInErrorMessage()
        }
    }

    private fun navigateToMain() {
        navigateTo<MainActivity>()
    }

    private fun showSignInErrorMessage() {
        snackBar(binding.root) { getString(R.string.error_message_invalid_sign_in) }
    }
}