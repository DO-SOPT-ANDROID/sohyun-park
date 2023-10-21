package org.sopt.dosopttemplate.features.account

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.core_ui.base.BindingActivity
import com.example.core_ui.context.snackBar
import com.example.core_ui.context.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.core.view.UiState
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.features.account.model.User
import org.sopt.dosopttemplate.features.util.Account.SIGN_UP_INFORMATION

@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

    private val viewModel by viewModels<SignUpViewModel>()

    override fun initView() {
        setClickEventOnSignUpLabelButton()
        collectSignUpValidity()
    }

    private fun setClickEventOnSignUpLabelButton() {
        binding.btnSignUpSignupLabel.setOnClickListener {
            viewModel.getSignUpValidity(saveSignUpInformation())
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

    private fun collectSignUpValidity() {
        viewModel.signUpValidity.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Failure -> showSignUpErrorMessage(it.errorMessage)
                is UiState.Success -> handleSignUpSuccess(it.data)
                is UiState.Loading -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun handleSignUpSuccess(inputSignUpInformation: User) {
        val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
        intent.putExtra(SIGN_UP_INFORMATION, inputSignUpInformation)
        setResult(RESULT_OK, intent)
        showSignUpSuccessMessage()
        finish()
    }

    private fun showSignUpErrorMessage(errorMessage: String) {
        snackBar(binding.root) { errorMessage }
    }

    private fun showSignUpSuccessMessage() {
        toast(getString(R.string.success_message_sign_up))
    }
}