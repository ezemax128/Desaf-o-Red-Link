package pumpkin.app.desafioredlink.presentation.view

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.os.HandlerCompat.postDelayed
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import pumpkin.app.desafioredlink.R
import pumpkin.app.desafioredlink.data.model.Album
import pumpkin.app.desafioredlink.data.model.Resource
import pumpkin.app.desafioredlink.databinding.FragmentListBinding
import pumpkin.app.desafioredlink.presentation.view.adapters.AlbumAdapter
import pumpkin.app.desafioredlink.presentation.viewModel.MainViewModel
import java.lang.Exception

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var binding: FragmentListBinding
    private val viewModel by activityViewModels<MainViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)
        //take control over back pressed
        var counter = 0
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            counter++
            when (counter) {
                1 -> {
                    Toast.makeText(
                        requireContext(),
                        "Presione 2 veces para salir",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> { requireActivity().finishAffinity()}
            }
        }
        setupListView()
    }

    private fun setupListView() {
        viewModel.getAlbums().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Success -> {
                    success(result.data)
                }
                is Resource.Loading -> {
                    loading()
                }
                is Resource.Failure -> {
                    failure(result.e)
                }
            }
        }
    }


    private fun success(data: List<Album>) {
        binding.progressBar.visibility = View.GONE
        setItemsInListView(data)
        setUpFilter(data)
    }

    private fun loading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun failure(e: Exception) {
        binding.progressBar.visibility = View.GONE
        Toast.makeText(
            requireContext(),
            "hay un problema: $e",
            Toast.LENGTH_LONG
        ).show()
    }

    //this function set items into ListView
    private fun setItemsInListView(list: List<Album>) {
        val listToShowInUI = binding.listToShow
        val adapter = AlbumAdapter(requireContext(), list)

        listToShowInUI.adapter = adapter

        listToShowInUI.setOnItemClickListener { parent, view, position, id ->
            val idToSend = list[position].id.toString()
            viewModel.setIdToSearch(idToSend)
            findNavController().navigate(R.id.action_listFragment_to_albumFragment)
        }
    }

    //this function make a list of all Albums Titles to use like a filter
    private fun setUpFilter(originalList: List<Album>) {
        val titlesAlbumsList = mutableListOf<String?>()
        titlesAlbumsList.add(getString(R.string.todos_albums))

        originalList.forEach { album ->
            titlesAlbumsList.add(album.title)
        }
        setUpSpinner(titlesAlbumsList, originalList)
    }


    //finally fill up the Spinner and ear the item selection
    private fun setUpSpinner(titlesAlbumsList: MutableList<String?>, originalList: List<Album>) {
        val spinner = binding.spinnerFilter
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            titlesAlbumsList
        )
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val newList = mutableListOf<Album>()
                val itemSelected = spinner.selectedItem
                for (i in originalList) {
                    if (i.title == itemSelected) {
                        newList.add(i)
                        setItemsInListView(newList)
                    } else if (itemSelected == getString(R.string.todos_albums)) {
                        setItemsInListView(originalList)
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //nothing to do
            }
        }
    }

}