package com.demo.letstrack.shared

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GenericBindingAdapter {

    companion object{
        @BindingAdapter(value = ["android:imageUrl", "android:placeholder"])
        @JvmStatic
        fun loadImageUrl(imageView: ImageView, imgUrl: String?, drawable: Drawable) {

            Glide.with(imageView.context)
                .load(imgUrl ?: drawable)
                .apply(
                    RequestOptions()
                        .placeholder(drawable)
                        .error(drawable)
                )
                .circleCrop()
                .into(imageView)
        }
    }
}