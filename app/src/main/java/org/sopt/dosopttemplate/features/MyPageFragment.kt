package org.sopt.dosopttemplate.features

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.core_ui.base.BindingFragment
import com.example.core_ui.fragment.snackBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.core.context.navigateTo
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding
import org.sopt.dosopttemplate.features.account.SignInActivity
import org.sopt.dosopttemplate.features.account.SignInViewModel
import org.sopt.dosopttemplate.features.account.model.User

class MyPageFragment : BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val signInViewModel by viewModels<SignInViewModel>()

    private val callback = object : OnBackPressedCallback(true) {
        private var doubleBackToExitPressedOnce = false
        override fun handleOnBackPressed() {
            if (doubleBackToExitPressedOnce) {
                remove()
                requireActivity().finish()
            } else {
                doubleBackToExitPressedOnce = true
                snackBar(binding.root) { getString(R.string.message_back_to_exit_pressed) }
                lifecycleScope.launch {
                    delay(BACK_TO_EXIT_DELAY_TIME)
                    doubleBackToExitPressedOnce = false
                }
            }
        }
    }

    override fun initView() {
        signInViewModel.getUserInformation()?.let { setMyPageInformation(it) }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
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

    companion object {
        const val BACK_TO_EXIT_DELAY_TIME = 2000L
    }
}