package org.sopt.dosopttemplate.features.doandroid

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.dosopttemplate.core.view.ItemDiffCallback
import org.sopt.dosopttemplate.databinding.ItemCarouselProfileBinding
import org.sopt.dosopttemplate.features.doandroid.viewholder.DoAndroidProfileViewHolder
import org.sopt.dosopttemplate.features.home.model.Profile

class DoAndroidAdapter(
    context: Context, private val onMoveToProfileDetailClick: (Profile, Int) -> Unit = { _, _ -> }
) :
    ListAdapter<Profile, DoAndroidProfileViewHolder>(DoAndroidAdapterDiffCallback) {
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoAndroidProfileViewHolder {
        val binding = ItemCarouselProfileBinding.inflate(inflater, parent, false)
        return DoAndroidProfileViewHolder(binding, onMoveToProfileDetailClick)
    }

    override fun onBindViewHolder(holder: DoAndroidProfileViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    companion object {
        private val DoAndroidAdapterDiffCallback = ItemDiffCallback<Profile>(
            onItemsTheSame = { old, new -> old.name == new.name },
            onContentsTheSame = { old, new -> old == new })
    }
}