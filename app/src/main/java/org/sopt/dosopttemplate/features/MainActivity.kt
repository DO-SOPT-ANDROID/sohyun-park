package org.sopt.dosopttemplate.features

import android.os.Build
import com.example.core_ui.base.BindingActivity
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.features.account.User
import org.sopt.dosopttemplate.features.util.Account


class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun initView() {
        setMyPageInformation(getUserInformation())
    }

    private fun getUserInformation(): User? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(Account.SIGN_UP_INFORMATION, User::class.java)
        } else {
            intent.getSerializableExtra(Account.SIGN_UP_INFORMATION) as User
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