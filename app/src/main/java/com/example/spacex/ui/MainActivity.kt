package com.example.spacex.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.spacex.adapter.RocketDetailAdapter
import com.example.spacex.databinding.ActivityMainBinding
import com.example.spacex.viewmodel.RocketListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: RocketListViewModel
    lateinit var rocketAdapter: RocketDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[RocketListViewModel::class.java]

        binding.rvRockets.apply {
            hasFixedSize() // Improve performance (use only with fixed size items)
            rocketAdapter = RocketDetailAdapter(mutableListOf())
            binding.rvRockets.apply {
                layoutManager = GridLayoutManager(applicationContext, 1)
                adapter = rocketAdapter
            }
            adapter = rocketAdapter
        }

        viewModel.rocketList.observe(this@MainActivity) {
            rocketAdapter.setData(it.toMutableList())
        }

        viewModel.isNetworkErrorShown.observe(this@MainActivity) {
            // Todo: display errors
        }

    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        viewModel.refreshDataFromRepository()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}