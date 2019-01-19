package com.onemonster.movienotes.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.onemonster.movienotes.R
import com.onemonster.movienotes.model.Status
import com.onemonster.movienotes.util.RecyclerViewPaginationScrollListener
import com.onemonster.movienotes.util.observeLiveData
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory)[MainActivityViewModel::class.java] }

    private val adapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeList()
        observeViewModel()
    }

    private fun initializeList() {
        val layoutManager = LinearLayoutManager(this)
        list.layoutManager = layoutManager
        list.adapter = adapter
        list.setOnScrollListener(RecyclerViewPaginationScrollListener(
            layoutManager,
            3,
            { viewModel.movieDiscoveryPagedResource.value?.status == Status.LOADING },
            { viewModel.movieDiscoveryPagedResource.value?.isLastPage == true },
            { viewModel.fetchMovieDiscovery(it) }
        ))
    }

    private fun observeViewModel() {
        observeLiveData(viewModel.movieDiscoveryPagedResource) { resource ->
            when (resource.status) {
                Status.SUCCESS -> resource.data?.let { adapter.add(it) }
                Status.ERROR -> Toast.makeText(this, resource.error?.statusMessage, Toast.LENGTH_SHORT).show()
                Status.LOADING -> Unit
            }
        }
    }
}