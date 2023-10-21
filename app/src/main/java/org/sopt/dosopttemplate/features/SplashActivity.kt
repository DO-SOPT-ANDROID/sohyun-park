package org.sopt.dosopttemplate.features

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.features.account.SignInActivity
import org.sopt.dosopttemplate.features.account.SignInViewModel
import org.sopt.dosopttemplate.features.main.MainActivity

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel by viewModels<SignInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        autoSignIn()
    }

    private fun autoSignIn() {
        if (viewModel.getCheckSignIn()) {
            navigateTo<MainActivity>()
        } else {
            navigateTo<SignInActivity>()
        }
    }

    private inline fun <reified T : Activity> navigateTo() {
        Intent(this@SplashActivity, T::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }
    }
}