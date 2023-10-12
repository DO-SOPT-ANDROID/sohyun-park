package org.sopt.dosopttemplate.features.account

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.core_ui.base.BindingActivity
import com.example.core_ui.context.snackBar
import com.example.core_ui.context.toast
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignInBinding
import org.sopt.dosopttemplate.features.MainActivity
import org.sopt.dosopttemplate.features.util.Account.SIGN_UP_INFORMATION

class SignInActivity : BindingActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var savedSignUpInformation: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setResultSignUpInformation()
    }

    override fun initView() {
        setResultSignUpInformation()
        setClickEventOnSignUpLabelButton()
        setClickEventOnSignInLabelButton()
    }

    private fun setClickEventOnSignUpLabelButton() {
        binding.btnSignInSignupLabel.setOnClickListener {
            navigateToSignUp()
        }
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

    private fun showSignInErrorMessage() {
        snackBar(binding.root) { getString(R.string.error_message_invalid_sign_in) }
    }

    private fun handleSignInSuccess() {
        toast(getString(R.string.error_message_valid_sign_in))
        navigateToMain()
    }

    private fun navigateToSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        resultLauncher.launch(intent)
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setResultSignUpInformation() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        savedSignUpInformation =
                            result.data?.getSerializableExtra(SIGN_UP_INFORMATION, User::class.java)
                                ?: return@registerForActivityResult

                        toast(savedSignUpInformation.toString())
                    } else {
                        savedSignUpInformation =
                            (result.data?.getSerializableExtra(SIGN_UP_INFORMATION)
                                ?: return@registerForActivityResult) as User?
                        toast(savedSignUpInformation.toString())
                    }
                }
            }
    }

    private fun checkIdIdentification(id: String): Boolean {
        return binding.etSignInId.text.toString() == id
    }

    private fun checkPwIdentification(pw: String): Boolean {
        return binding.etSignInPw.text.toString() == pw
    }


}