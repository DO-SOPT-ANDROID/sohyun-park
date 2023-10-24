package org.sopt.dosopttemplate.features.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemHomeBirthdayFriendBinding
import org.sopt.dosopttemplate.features.home.model.Profile

class BirthdayFriendProfileViewHolder(private val binding: ItemHomeBirthdayFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: Profile.BirthdayFriend) {
        with(binding) {
            profile = data
            executePendingBindings()
        }
    }
}
