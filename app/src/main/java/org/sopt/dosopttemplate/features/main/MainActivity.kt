package org.sopt.dosopttemplate.features.main

import androidx.activity.viewModels
import com.example.core_ui.base.BindingActivity
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.domain.entity.User

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModels<MainViewModel>()

    override fun initView() {
        setMyPageInformation(viewModel.getUserInformation())
    }

    private fun setMyPageInformation(user: User) {
        with(binding) {
            tvMainId.text = user.id
            tvMainNickname.text = user.nickname
            tvMainDrinkingCapacity.text =
                user.drinkingCapacity
        }
    }
}