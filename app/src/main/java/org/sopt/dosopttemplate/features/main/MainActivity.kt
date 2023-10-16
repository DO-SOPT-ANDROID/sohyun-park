package org.sopt.dosopttemplate.features.main

import android.os.Build
import com.example.core_ui.base.BindingActivity
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.features.account.model.User
import org.sopt.dosopttemplate.features.util.Account


class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun initView() {
        setMyPageInformation(getUserInformation())
    }

    private fun getUserInformation(): User? {
        val signUpInformation = intent.getSerializableExtra(Account.SIGN_UP_INFORMATION)
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> signUpInformation as? User
            signUpInformation is User -> signUpInformation
            else -> null
        }
    }

    private fun setMyPageInformation(user: User?) {
        with(binding) {
            tvMainId.text = user?.id ?: getString(R.string.error_message_empty_data)
            tvMainNickname.text = user?.nickname ?: getString(R.string.error_message_empty_data)
            tvMainDrinkingCapacity.text =
                user?.drinkingCapacity ?: getString(R.string.error_message_empty_data)
        }
    }
}