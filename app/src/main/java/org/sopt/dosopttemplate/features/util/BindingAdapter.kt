package org.sopt.dosopttemplate.features.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import org.sopt.dosopttemplate.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    val imageResource = if (url.isNullOrEmpty()) R.drawable.img_empty_profile_image else url
    view.load(imageResource)
}

//BindingAdaptor 사용하기
@BindingAdapter("setCircleImage")
fun ImageView.setCircleImage(img: String?) {
    val imageResource = if (img.isNullOrEmpty()) R.drawable.img_empty_profile_image else img

    load(imageResource) {
        transformations(RoundedCornersTransformation(60f))
    }
}