package org.sopt.dosopttemplate.features.account

import android.content.Intent
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.core_ui.base.BindingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.core.context.navigateTo
import org.sopt.dosopttemplate.core.context.toast
import org.sopt.dosopttemplate.core.view.UiState
import org.sopt.dosopttemplate.databinding.ActivitySignInBinding
import org.sopt.dosopttemplate.domain.entity.UserEntity
import org.sopt.dosopttemplate.features.MainActivity
import org.sopt.dosopttemplate.features.account.model.User

@AndroidEntryPoint
class SignInActivity : BindingActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private lateinit var savedSignUpInformation: User
    private val viewModel by viewModels<SignInViewModel>()

    override fun initView() {
        setClickEventOnSignUpLabelButton()
        setClickEventOnSignInLabelButton()
        collectPostSignIn()
    }

    override fun onResume() {
        super.onResume()
        setUserInformation()
    }

    private fun setUserInformation(): UserEntity {
        with(binding) {
            return UserEntity(etSignInId.text.toString(), etSignInPw.text.toString())
        }
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
            viewModel.postSignIn(setUserInformation())
        }
    }

    private fun collectPostSignIn() {
        viewModel.postSignIn.flowWithLifecycle(lifecycle).onEach {

            when (it) {
                is UiState.Success -> it.data?.let { userIdEntity ->
                    handleSignInSuccess(
                        userIdEntity.userId
                    )
                } ?: run { binding.tvSignInErrorMessage.isVisible = true }

                is UiState.Failure -> binding.tvSignInErrorMessage.isVisible = true
                is UiState.Loading -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    private fun handleSignInSuccess(userId: Int?) {
        viewModel.setCheckSignIn(true)
        toast(getString(R.string.success_message_valid_sign_in) + "userId : $userId")
        navigateTo<MainActivity>()
    }
}