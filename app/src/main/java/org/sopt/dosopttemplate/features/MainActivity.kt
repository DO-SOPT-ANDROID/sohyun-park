package org.sopt.dosopttemplate.features

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import com.example.core_ui.base.BindingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.core.context.snackBar
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.features.doandroid.DoAndroidFragment
import org.sopt.dosopttemplate.features.home.HomeFragment
import org.sopt.dosopttemplate.features.mypage.MyPageFragment

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

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
                    delay(BACK_TO_EXIT_DELAY_TIME)
                    doubleBackToExitPressedOnce = false
                }
            }
        }
    }

    override fun initView() {
        setFragment()
        setClickEventOnBottomNavigationItem()
        binding.bnvMain.itemIconTintList = null
        onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun setFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        currentFragment ?: navigateTo<HomeFragment>()
    }

    private fun setClickEventOnBottomNavigationItem() {
        binding.bnvMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> navigateTo<HomeFragment>()
                R.id.menu_do_android -> navigateTo<DoAndroidFragment>()
                R.id.menu_my_page -> navigateTo<MyPageFragment>()
            }
            true
        }
    }

    private inline fun <reified T : Fragment> navigateTo() {
        supportFragmentManager.commit {
            replace<T>(R.id.fcv_main, T::class.java.canonicalName)
        }
    }

    companion object {
        const val BACK_TO_EXIT_DELAY_TIME = 2000L
    }
}