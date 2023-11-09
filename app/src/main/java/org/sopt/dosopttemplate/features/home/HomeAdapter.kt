package org.sopt.dosopttemplate.features.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.sopt.dosopttemplate.core.view.ItemDiffCallback
import org.sopt.dosopttemplate.databinding.ItemHomeBirthdayFriendBinding
import org.sopt.dosopttemplate.databinding.ItemHomeFriendProfileBinding
import org.sopt.dosopttemplate.databinding.ItemHomeMyProfileBinding
import org.sopt.dosopttemplate.features.home.model.Profile
import org.sopt.dosopttemplate.features.home.viewholder.BirthdayFriendProfileViewHolder
import org.sopt.dosopttemplate.features.home.viewholder.FriendProfileViewHolder
import org.sopt.dosopttemplate.features.home.viewholder.MyProfileViewHolder

class HomeAdapter(context: Context) :
    ListAdapter<Profile, ViewHolder>(HomeAdapterDiffCallback) {
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is Profile.My -> VIEW_TYPE_MY_PROFILE
            is Profile.Friend -> VIEW_TYPE_FRIEND_PROFILE
            is Profile.BirthdayFriend -> VIEW_TYPE_BIRTHDAY_FRIEND_PROFILE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MY_PROFILE -> {
                val binding = ItemHomeMyProfileBinding.inflate(inflater, parent, false)
                MyProfileViewHolder(binding)
            }

            VIEW_TYPE_BIRTHDAY_FRIEND_PROFILE -> {
                val binding = ItemHomeBirthdayFriendBinding.inflate(inflater, parent, false)
                BirthdayFriendProfileViewHolder(binding)
            }

            else -> {
                val binding = ItemHomeFriendProfileBinding.inflate(inflater, parent, false)
                FriendProfileViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val data = currentList[position]) {
            is Profile.My -> (holder as MyProfileViewHolder).run { onBind(data) }
            is Profile.BirthdayFriend -> (holder as BirthdayFriendProfileViewHolder).run {
                onBind(
                    data
                )
            }

            is Profile.Friend -> (holder as FriendProfileViewHolder).run { onBind(data) }
        }
    }

    companion object {
        private val HomeAdapterDiffCallback = ItemDiffCallback<Profile>(
            onItemsTheSame = { old, new -> old.name == new.name },
            onContentsTheSame = { old, new -> old == new })

        const val VIEW_TYPE_MY_PROFILE = 0
        const val VIEW_TYPE_FRIEND_PROFILE = 1
        const val VIEW_TYPE_BIRTHDAY_FRIEND_PROFILE = 2
    }
}