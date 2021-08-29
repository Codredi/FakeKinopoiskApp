package com.evanstukalov.fakekinopoiskapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.evanstukalov.fakekinopoiskapp.R
import com.evanstukalov.fakekinopoiskapp.databinding.FragmentDetailBinding
import com.evanstukalov.fakekinopoiskapp.ui.list.ListViewModel
import com.evanstukalov.fakekinopoiskapp.utils.bindImage
import timber.log.Timber

class DetailFragment : Fragment() {

    val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        // Getting response from safe args
        val film = args.selectedFilm

        // Binding film in layout with response result
        binding.film = film

        // Setting title
        (requireActivity() as AppCompatActivity).supportActionBar?.title = film.localizedName

        return binding.root
    }

}