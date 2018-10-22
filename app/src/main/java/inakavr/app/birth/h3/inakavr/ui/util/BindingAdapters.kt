package inakavr.app.birth.h3.inakavr.ui.util

import android.databinding.BindingAdapter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * 画像 URLバインディング
 */
@BindingAdapter("android:src")
fun setImageUrl(imageView: ImageView, url: String) {
    val requestOptions: RequestOptions = RequestOptions()
    requestOptions.placeholder(ColorDrawable(Color.GRAY))
            .override(300, 300)
            .dontAnimate()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    Glide.with(imageView.context)
            .load(url)
            .apply(requestOptions)
            .into(imageView)
}