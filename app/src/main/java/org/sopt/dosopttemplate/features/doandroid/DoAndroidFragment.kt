package org.sopt.dosopttemplate.features.doandroid

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.core_ui.base.BindingFragment
import com.google.android.material.carousel.CarouselSnapHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.core.view.UiState
import org.sopt.dosopttemplate.databinding.FragmentDoAndroidBinding
import org.sopt.dosopttemplate.features.home.mapper.toProfileList
import org.sopt.dosopttemplate.features.home.model.Profile
import org.sopt.dosopttemplate.features.util.EventObserver
import org.sopt.dosopttemplate.features.util.Profile.PROFILE_INFORMATION
import timber.log.Timber

@AndroidEntryPoint
class DoAndroidFragment : BindingFragment<FragmentDoAndroidBinding>(R.layout.fragment_do_android) {

    private val viewModel by viewModels<DoAndroidViewModel>()

    override fun initView() {
        collectProfile()
        observeProfileClickEvent()
    }

    private fun collectProfile() {
        viewModel.getProfile.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> Unit
                is UiState.Success -> if (binding.rvDoAndroidProfile.adapter == null) setupRecyclerView(
                    it.data.toProfileList()
                )

                is UiState.Failure -> Timber.d(getString(R.string.error_message_get_profile_data))
            }
        }.launchIn(lifecycleScope)
    }

    private fun setupRecyclerView(profileList: List<Profile>) {
        val snapHelper = CarouselSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvDoAndroidProfile)

        binding.rvDoAndroidProfile.adapter = DoAndroidAdapter(requireContext()) { profileInfo, _ ->
            viewModel.openProfileDetail(profileInfo)
        }.apply {
            submitList(profileList)
        }
    }

    private fun observeProfileClickEvent() {
        viewModel.openProfileDetail.observe(viewLifecycleOwner, EventObserver {
            navigateToProfileDetail(it)
        })
    }

    private fun navigateToProfileDetail(profileInfo: Profile) {
        val intent = Intent(requireActivity(), DoAndroidProfileDetailActivity::class.java)
        intent.putExtra(PROFILE_INFORMATION, profileInfo)
        startActivity(intent)
    }
}