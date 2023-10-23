package org.sopt.dosopttemplate.features

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.core.context.navigateTo
import org.sopt.dosopttemplate.features.account.SignInActivity
import org.sopt.dosopttemplate.features.account.SignInViewModel

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel by viewModels<SignInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        autoSignIn()
    }

    private fun autoSignIn() {
        if (viewModel.getCheckSignIn()) {
            navigateTo<MyPageActivity>()
        } else {
            navigateTo<SignInActivity>()
        }
    }
}