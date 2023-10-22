package org.sopt.dosopttemplate.features.main

import android.content.Intent
import androidx.activity.viewModels
import com.example.core_ui.base.BindingActivity
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.domain.entity.UserEntity
import org.sopt.dosopttemplate.features.account.SignInActivity
import org.sopt.dosopttemplate.features.account.SignInViewModel

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val signInViewModel by viewModels<SignInViewModel>()

    override fun initView() {
        setMyPageInformation(signInViewModel.getUserInformation())
        setClickEventOnSignOutLabelButton()
    }

    private fun setMyPageInformation(user: UserEntity) {
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