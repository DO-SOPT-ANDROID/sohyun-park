package org.sopt.dosopttemplate.features.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import org.sopt.dosopttemplate.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    view.load(url)
}

@BindingAdapter("setCircleImage")
fun ImageView.setCircleImage(img: String?) {
    if (img.isNullOrEmpty()) {
        load(R.drawable.img_empty_profile_image)
    } else {
        load(img) {
            transformations(RoundedCornersTransformation(500f))
        }
    }
}