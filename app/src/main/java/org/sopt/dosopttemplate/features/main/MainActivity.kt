package org.sopt.dosopttemplate.features.main

import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.core_ui.base.BindingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.core.context.navigateTo
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
                snackBar(binding.root) { getString(R.string.message_back_to_exit_pressed) }
                lifecycleScope.launch {
                    delay(2000L)
                    doubleBackToExitPressedOnce = false
                }
            }
        }
    }

    override fun initView() {
        signInViewModel.getUserInformation()?.let { setMyPageInformation(it) }
        this.onBackPressedDispatcher.addCallback(this, callback)
        setClickEventOnSignOutLabelButton()
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
            navigateTo<SignInActivity>()
        }
    }
}