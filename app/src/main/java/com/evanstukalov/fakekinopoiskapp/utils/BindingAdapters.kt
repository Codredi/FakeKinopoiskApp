package com.evanstukalov.fakekinopoiskapp.utils

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.evanstukalov.fakekinopoiskapp.R

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    imgUrl?.let {
        Glide.with(imgView.context)
            .load(imgUrl.toUri().buildUpon().scheme("https").build())
            .error(R.drawable.ic_network_problem)
            .fallback(R.drawable.ic_broken_image)
            .placeholder(R.drawable.loading_animation)
            .apply(RequestOptions().centerCrop())
            .into(imgView)
    }
}

@BindingAdapter("textYear")
fun mapYear(textView: TextView, year: Int?){
    textView.text = textView.context.getString(R.string.year, year)
}

@BindingAdapter("textRate")
fun mapRating(textView: TextView, rating: Double?){
//    textView.text = "Рейтинг: $year"

    val spannable = SpannableString("Рейтинг: $rating")
    spannable.setSpan(ForegroundColorSpan(Color.GREEN), 8, spannable.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    textView.text = spannable
}

