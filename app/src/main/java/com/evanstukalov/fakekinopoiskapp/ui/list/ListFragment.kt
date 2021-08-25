package com.evanstukalov.fakekinopoiskapp.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.evanstukalov.fakekinopoiskapp.R
import com.evanstukalov.fakekinopoiskapp.database.getDatabase
import com.evanstukalov.fakekinopoiskapp.databinding.FragmentListBinding
import com.evanstukalov.fakekinopoiskapp.domain.Film
import com.evanstukalov.fakekinopoiskapp.ui.detail.are FiDetailFragmentDirections
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
        Timber.d("ListFragment is created")
        // Inflate the layout for this fragment
        val binding: FragmentListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


        val adapter = MyListAdapter{film -> viewModel.displayFilmDetails(film) }
        /**
         * Method for displaying a Toast error message for network errors.
         */
        binding.filmsRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            this.adapter = adapter
        }

        // Observer for the network error.
        viewModel.apply {
            eventNetworkError.observe(viewLifecycleOwner, Observer { isNetworkError ->
                if (isNetworkError) onNetworkError()
            })
            films.observe(viewLifecycleOwner, Observer { films ->
                films?.let {
                    adapter.submitList(it)
                }
            })
            navigateToSelectedFilm.observe(viewLifecycleOwner, Observer { film ->
                if (film != null){
                    findNavController().navigate()
                    viewModel.displayPropertyDetailesCompleted()
                }
            })
        }

        return binding.root
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

}