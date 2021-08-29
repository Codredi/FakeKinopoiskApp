package com.evanstukalov.fakekinopoiskapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.evanstukalov.fakekinopoiskapp.R
import com.evanstukalov.fakekinopoiskapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()

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

        // Setting option menu in fragment
        setHasOptionsMenu(true)

        return binding.root
    }

}