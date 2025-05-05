package com.example.httpsampleapp.ui.screen.imagelist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.httpsampleapp.R
import com.example.httpsampleapp.data.api.RetrofitClient
import com.example.httpsampleapp.data.datasource.RemoteDataSource
import com.example.httpsampleapp.data.repository.ImageRepositoryImpl
import com.example.httpsampleapp.databinding.ActivityMainBinding
import com.example.httpsampleapp.domain.usecase.GetImageUseCase

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ImageListViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        init()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun init() {
        val api = RetrofitClient.api
        val dataSource = RemoteDataSource(api)
        val repository = ImageRepositoryImpl(dataSource)
        val useCase = GetImageUseCase(repository)
        val factory = ImageListViewModelFactory(useCase)
        viewModel = ViewModelProvider(this@MainActivity, factory).get(ImageListViewModel::class.java)

        binding = ActivityMainBinding.inflate(layoutInflater)
        adapter = ImageAdapter()
        binding.recyclerViewImageList.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        binding.recyclerViewImageList.adapter = adapter

        viewModel.state.observe(this) { state ->
            when(state) {
                is ImageUiState.Loading -> {
                    binding.progressLoading.visibility = View.VISIBLE
                }
                is ImageUiState.Success -> {
                    binding.progressLoading.visibility = View.GONE
                    adapter.submitList(state.images)
                }
                is ImageUiState.Error -> Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loadImages()
    }
}