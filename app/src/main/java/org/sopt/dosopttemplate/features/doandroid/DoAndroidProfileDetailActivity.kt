package org.sopt.dosopttemplate.features.doandroid

import android.os.Build

import com.example.core_ui.base.BindingActivity
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityProfileDetailBinding
import org.sopt.dosopttemplate.features.home.model.Profile
import org.sopt.dosopttemplate.features.util.Profile.PROFILE_INFORMATION

@AndroidEntryPoint
class DoAndroidProfileDetailActivity :
    BindingActivity<ActivityProfileDetailBinding>(R.layout.activity_profile_detail) {
    override fun initView() {
        setProfileDetail()
    }

    private fun setProfileDetail() {
        binding.profile = extractProfileDetail()
    }

    private fun extractProfileDetail(): Profile? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(PROFILE_INFORMATION, Profile::class.java)
        } else {
            intent.getParcelableExtra(PROFILE_INFORMATION) as? Profile ?: Profile.My(
                " ", getString(
                    R.string.error_message_get_profile_data
                )
            )
        }
    }
}