package com.evanstukalov.fakekinopoiskapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evanstukalov.fakekinopoiskapp.R
import timber.log.Timber

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Timber.d("DetailFragment is created")
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

}