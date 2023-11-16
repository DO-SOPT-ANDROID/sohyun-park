package org.sopt.dosopttemplate.features.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.core_ui.base.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.core.view.UiState
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.features.home.mapper.toFriendProfiles
import org.sopt.dosopttemplate.features.home.mapper.toProfileList
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun initView() {
        collectReqresListUsers()
    }

    private fun collectProfile() {
        viewModel.getProfile.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> Unit
                is UiState.Success -> {
                    binding.rvHomeProfile.adapter = HomeAdapter(requireContext()).apply {
                        submitList(it.data.toProfileList())
                    }
                }

                is UiState.Failure -> Timber.d(getString(R.string.error_message_get_profile_data))
            }
        }.launchIn(lifecycleScope)
    }

    private fun collectReqresListUsers() {
        viewModel.getReqresListUsers.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> binding.rvHomeProfile.adapter =
                    HomeAdapter(requireContext()).apply {
                        submitList(it.data?.toFriendProfiles())
                    }

                is UiState.Failure -> Timber.d(getString(R.string.error_message_get_profile_data))
                is UiState.Loading -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    fun setRecyclerviewSmoothScrollToTop() {
        binding.rvHomeProfile.smoothScrollToPosition(0)
    }
}