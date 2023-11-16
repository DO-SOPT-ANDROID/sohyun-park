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
import org.sopt.dosopttemplate.domain.entity.UserEntity
import timber.log.Timber

@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

    private val signUpViewModel by viewModels<SignUpViewModel>()

    override fun initView() {
        setClickEventOnSignUpLabelButton()
        collectSignUpValidity()
        collectPostSignUp()
    }

    private fun setClickEventOnSignUpLabelButton() {
        binding.btnSignUpSignupLabel.setOnClickListener {
            signUpViewModel.getSignUpValidity(saveSignUpInformation())
        }
    }

    private fun saveSignUpInformation(): UserEntity {
        with(binding) {
            return UserEntity(
                viewSignUpId.etSignUp.text?.toString().orEmpty(),
                viewSignUpPw.etSignUp.text?.toString().orEmpty(),
                viewSignUpNickname.etSignUp.text?.toString().orEmpty(),
                viewSignUpDrinkingCapacity.etSignUp.text?.toString().orEmpty()
            )
        }
    }

    private fun collectSignUpValidity() {
        signUpViewModel.signUpValidity.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> signUpViewModel.postSignUp(it.data)
                is UiState.Failure -> snackBar(binding.root) { it.errorMessage }
                is UiState.Loading -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    private fun collectPostSignUp() {
        signUpViewModel.postSignUp.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> it.data?.let { handleSignUpSuccess(saveSignUpInformation()) }
                    ?: run { snackBar(binding.root) { getString(R.string.errorMessage400) } }


                is UiState.Failure -> Timber.d(it.errorMessage)
                is UiState.Loading -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    private fun handleSignUpSuccess(inputSignUpInformation: UserEntity) {
        signUpViewModel.setUserInformation(inputSignUpInformation)
        toast(getString(R.string.success_message_sign_up))
        finish()
    }
}