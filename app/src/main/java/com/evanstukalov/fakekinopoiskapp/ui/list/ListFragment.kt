package com.evanstukalov.fakekinopoiskapp.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.evanstukalov.fakekinopoiskapp.R
import com.evanstukalov.fakekinopoiskapp.database.getDatabase
import com.evanstukalov.fakekinopoiskapp.databinding.FragmentListBinding
import com.google.android.material.chip.Chip
import timber.log.Timber

class ListFragment : Fragment() {

    private val viewModel: ListViewModel by lazy {
        val activity = requireNotNull(this.activity)
        val database = getDatabase(activity.applicationContext)
        ViewModelProvider(this, ListViewModel.Factory(activity.application, database))
            .get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


        // Film adapter
        val filmAdapter = ListFilmsAdapter{ film -> viewModel.displayFilmDetails(film) }

        // Film recyclerview
        binding.filmsRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            this.adapter = filmAdapter
        }

        // ViewModel observers
        viewModel.apply {
            eventNetworkError.observe(viewLifecycleOwner, Observer { isNetworkError ->
                if (isNetworkError) onNetworkError()
            })
            films.observe(viewLifecycleOwner, Observer { films ->
                films?.let {
                    filmAdapter.submitList(it)
                }
            })
            genres.observe(viewLifecycleOwner, Observer { genres ->
                makeChips(genres, binding)
            })
            navigateToSelectedFilm.observe(viewLifecycleOwner, Observer { film ->
                if (film != null){
                    findNavController().navigate(ListFragmentDirections.actionShowDetail(film))
                    viewModel.displayPropertyDetailesCompleted()
                }
            })
        }

        // Setting listeners on individual chip
        binding.chipGroup.setOnCheckedChangeListener { chipGroup, checkedId ->
            val chip = chipGroup.findViewById<Chip>(checkedId)
            if (chip != null){
                chip.setCheckedIconResource(R.drawable.ic_mtrl_chip_checked_black)
                var genre = chip.text.toString()
                genre = "%$genre%"
                getCertainFilms(filmAdapter, genre)
            }
        }

        // Setting option menu in fragment
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun getCertainFilms(filmAdapter: ListFilmsAdapter, genre: String) {
        viewModel.getCertainFilms(genre).observe(viewLifecycleOwner, Observer {
            filmAdapter.submitList(it)
        })
        Timber.d("$genre - ???????????????????????? ????????")
    }

    // Make chips programmatically
    private fun makeChips(genres: List<String>?, binding: FragmentListBinding) {
        val chipGroup = binding.chipGroup

        if (genres != null) {
            for (chip in genres){
                if (chipGroup.size < genres.size){
                    val chipItem = Chip(context)

                    chipItem.isCheckable = true
                    chipItem.text = chip
                    chipGroup.addView(chipItem)
                }
            }
        }
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}