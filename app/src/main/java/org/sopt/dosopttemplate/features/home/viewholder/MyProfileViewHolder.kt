package org.sopt.dosopttemplate.features.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemHomeMyProfileBinding
import org.sopt.dosopttemplate.features.home.model.Profile

class MyProfileViewHolder(private val binding: ItemHomeMyProfileBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: Profile.My) {
    }
}
