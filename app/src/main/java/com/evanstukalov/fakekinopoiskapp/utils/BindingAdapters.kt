package com.evanstukalov.fakekinopoiskapp.utils

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



//@BindingAdapter("imageUrl")
//fun mapText(textView: TextView, value: String){
//    var newString: String = ""
//    if (textView.lineCount >= 3){
//        textView.setLines(2)
//        newString = value.substring(0..(value.count() - 3)).plus("...")
//    }
//    textView.text = newString
//}