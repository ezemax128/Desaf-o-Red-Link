package pumpkin.app.desafioredlink.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import pumpkin.app.desafioredlink.R
import pumpkin.app.desafioredlink.data.model.Photo
import pumpkin.app.desafioredlink.data.model.Resource
import pumpkin.app.desafioredlink.databinding.FragmentAlbumBinding
import pumpkin.app.desafioredlink.presentation.view.adapters.RecyclerAdapter
import pumpkin.app.desafioredlink.presentation.viewModel.MainViewModel
import java.lang.Exception

@AndroidEntryPoint
class AlbumFragment : Fragment(R.layout.fragment_album) {

    private lateinit var binding: FragmentAlbumBinding
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlbumBinding.bind(view)
        setupToolBar()
        loadData()
    }

    private fun setupToolBar() {
        val toolBar = binding.toolbar2
        val navController = findNavController()
        val appBarConfiguration =  AppBarConfiguration(navController.graph)
        toolBar.setupWithNavController(navController, appBarConfiguration)
        toolBar.title = "Red Link"
    }

    private fun loadData() {
        viewModel.idtoSearch.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    success(it.data)
                }
                is Resource.Loading -> {
                    loading()
                }
                is Resource.Failure -> {
                    failure(it.e)
                }
            }
        }
    }

    private fun loading() {
        binding.progressBar2.visibility = View.VISIBLE
    }

    private fun success(data: List<Photo>) {
        binding.progressBar2.visibility = View.GONE
        val recycler = binding.RecyclePhotos
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        val adapter = RecyclerAdapter(data)
        recycler.adapter = adapter
    }

    private fun failure(e: Exception) {
        binding.progressBar2.visibility = View.GONE
        Toast.makeText(requireContext(), "Lo sentimos, algo no salió Bien $e", Toast.LENGTH_LONG)
            .show()
    }
}