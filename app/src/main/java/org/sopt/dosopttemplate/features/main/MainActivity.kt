package org.sopt.dosopttemplate.features.main

import android.content.Intent
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.core_ui.base.BindingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.core.context.snackBar
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.features.account.SignInActivity
import org.sopt.dosopttemplate.features.account.SignInViewModel
import org.sopt.dosopttemplate.features.account.model.User

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val signInViewModel by viewModels<SignInViewModel>()

    private val callback = object : OnBackPressedCallback(true) {
        private var doubleBackToExitPressedOnce = false
        override fun handleOnBackPressed() {
            if (doubleBackToExitPressedOnce) {
                remove()
                finish()
            } else {
                doubleBackToExitPressedOnce = true
                snackBar(binding.root) { "뒤로 가기 버튼을 한 번 더 누르면 종료됩니다." }
                lifecycleScope.launch {
                    delay(2000L)
                    doubleBackToExitPressedOnce = false
                }
            }
        }
    }

    override fun initView() {
        signInViewModel.getUserInformation()?.let { setMyPageInformation(it) }
        setClickEventOnSignOutLabelButton()
        this.onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun setMyPageInformation(user: User) {
        with(binding) {
            tvMainId.text = user.id
            tvMainNickname.text = user.nickname
            tvMainDrinkingCapacity.text =
                user.drinkingCapacity
        }
    }

    private fun setClickEventOnSignOutLabelButton() {
        binding.btnMainSignOutLabel.setOnClickListener {
            signInViewModel.setCheckSignIn(false)
            navigateToSignIn()
        }
    }

    private fun navigateToSignIn() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }
}