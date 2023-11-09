package org.sopt.dosopttemplate.features.doandroid.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemCarouselProfileBinding
import org.sopt.dosopttemplate.features.home.model.Profile

class DoAndroidProfileViewHolder(
    private val binding: ItemCarouselProfileBinding,
    private val onMoveToProfileDetailClick: (Profile, Int) -> Unit = { _, _ -> }
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: Profile) {
        with(binding) {
            profile = data
            root.setOnClickListener {
                onMoveToProfileDetailClick(data, adapterPosition)
            }
            executePendingBindings()
        }
    }
}