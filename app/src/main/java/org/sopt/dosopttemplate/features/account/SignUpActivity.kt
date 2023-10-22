package org.sopt.dosopttemplate.features.account

import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.core_ui.base.BindingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.core.context.snackBar
import org.sopt.dosopttemplate.core.context.toast
import org.sopt.dosopttemplate.core.view.UiState
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.features.account.model.User

@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

    private val signUpViewModel by viewModels<SignUpViewModel>()
    private val signInViewModel by viewModels<SignInViewModel>()

    override fun initView() {
        setClickEventOnSignUpLabelButton()
        collectSignUpValidity()
    }

    private fun setClickEventOnSignUpLabelButton() {
        binding.btnSignUpSignupLabel.setOnClickListener {
            signUpViewModel.getSignUpValidity(saveSignUpInformation())
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
        signUpViewModel.signUpValidity.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> handleSignUpSuccess(it.data)
                is UiState.Failure -> snackBar(binding.root) { it.errorMessage }
                is UiState.Loading -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun handleSignUpSuccess(inputSignUpInformation: User) {
        signInViewModel.setUserInformation(inputSignUpInformation)
        toast(getString(R.string.success_message_sign_up))
        finish()
    }
}