package org.sopt.dosopttemplate.features.account

import android.content.Intent
import androidx.activity.viewModels
import com.example.core_ui.base.BindingActivity
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.core.context.navigateTo
import org.sopt.dosopttemplate.core.context.snackBar
import org.sopt.dosopttemplate.core.context.toast
import org.sopt.dosopttemplate.databinding.ActivitySignInBinding
import org.sopt.dosopttemplate.features.MainActivity
import org.sopt.dosopttemplate.features.account.model.User

@AndroidEntryPoint
class SignInActivity : BindingActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private lateinit var savedSignUpInformation: User
    private val viewModel by viewModels<SignInViewModel>()

    override fun initView() {
        setClickEventOnSignUpLabelButton()
        setClickEventOnSignInLabelButton()
    }

    override fun onResume() {
        super.onResume()
        setUserInformation()
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

    private fun handleSignIn() {
        if (this::savedSignUpInformation.isInitialized && isSignInSuccessful(savedSignUpInformation)) {
            handleSignInSuccess()
        } else {
            snackBar(binding.root) { getString(R.string.error_message_invalid_sign_in) }
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
        toast(getString(R.string.success_message_valid_sign_in))
        navigateTo<MainActivity>()
    }
}