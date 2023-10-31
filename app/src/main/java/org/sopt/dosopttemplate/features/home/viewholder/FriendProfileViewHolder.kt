package org.sopt.dosopttemplate.features.home.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemHomeFriendProfileBinding
import org.sopt.dosopttemplate.features.home.model.Profile

class FriendProfileViewHolder(private val binding: ItemHomeFriendProfileBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: Profile.Friend) {
        with(binding) {
            if (data.melonMusic.isNullOrEmpty()) tvItemHomeFriendProfileMelonMusic.visibility =
                View.GONE
            profile = data
            executePendingBindings()
        }
    }
}
