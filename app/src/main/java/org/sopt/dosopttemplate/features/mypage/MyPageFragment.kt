package org.sopt.dosopttemplate.features

import androidx.fragment.app.viewModels
import com.example.core_ui.base.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.core.context.navigateTo
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding
import org.sopt.dosopttemplate.features.account.SignInActivity
import org.sopt.dosopttemplate.features.account.SignInViewModel
import org.sopt.dosopttemplate.features.account.model.User

@AndroidEntryPoint
class MyPageFragment : BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val signInViewModel by viewModels<SignInViewModel>()

    override fun initView() {
        signInViewModel.getUserInformation()?.let { setMyPageInformation(it) }
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
            requireActivity().navigateTo<SignInActivity>()
        }
    }
}