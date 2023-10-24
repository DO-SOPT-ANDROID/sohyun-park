package org.sopt.dosopttemplate.features.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import com.example.core_ui.base.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.core.view.UiState
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.features.home.mapper.toProfileList
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun initView() {
        collectProfile()
    }

    private fun collectProfile() {
        viewModel.getProfile.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> {}
                is UiState.Success -> {
                    binding.rvHomeProfile.adapter = HomeAdapter(requireContext()).apply {
                        setDataList(it.data.toProfileList())
                    }
                }

                is UiState.Failure -> {
                    Timber.d("profile 데이터 수신 실패")
                }
            }
        }
    }


}