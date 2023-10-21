package org.sopt.dosopttemplate.features.account

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.core_ui.base.BindingActivity
import com.example.core_ui.context.snackBar
import com.example.core_ui.context.toast
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignInBinding
import org.sopt.dosopttemplate.features.account.model.User
import org.sopt.dosopttemplate.features.main.MainActivity
import org.sopt.dosopttemplate.features.util.Account.SIGN_UP_INFORMATION

@AndroidEntryPoint
class SignInActivity : BindingActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var savedSignUpInformation: User? = null
    private val viewModel by viewModels<SignInViewModel>()

    override fun initView() {
        setResultSignUpInformation()
        setClickEventOnSignUpLabelButton()
        setClickEventOnSignInLabelButton()
    }

    private fun setResultSignUpInformation() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    savedSignUpInformation = extractSignUpInformation(result.data)
                }
            }
    }

    private fun extractSignUpInformation(data: Intent?): User? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            data?.getParcelableExtra(SIGN_UP_INFORMATION, User::class.java)
        } else {
            data?.getParcelableExtra(SIGN_UP_INFORMATION) as? User
        }
    }

    private fun setClickEventOnSignUpLabelButton() {
        binding.btnSignInSignupLabel.setOnClickListener {
            navigateToSignUp()
        }
    }

    private fun navigateToSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        resultLauncher.launch(intent)
    }

    private fun setClickEventOnSignInLabelButton() {
        binding.btnSignInSigninLabel.setOnClickListener {
            savedSignUpInformation?.let {
                if (isSignInSuccessful(it)) handleSignInSuccess() else showSignInErrorMessage()
            }
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

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        savedSignUpInformation?.let { viewModel.setUserInformation(it) }
        startActivity(intent)
        finish()
    }

    private fun showSignInErrorMessage() {
        snackBar(binding.root) { getString(R.string.error_message_invalid_sign_in) }
    }
}