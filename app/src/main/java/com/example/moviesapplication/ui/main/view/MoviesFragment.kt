package com.example.moviesapplication.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.Sourceapp.ui.main.adapter.MoviesAdapter
import com.example.moviesapplication.R
import com.example.moviesapplication.data.local.db.entities.Movies
import com.example.moviesapplication.ui.main.viewmodel.MoviesViewModel
import com.example.moviesapplication.utils.ItemOffsetDecoration
import com.example.moviesapplication.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies.*



@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val moviesViewModel: MoviesViewModel by viewModels()
    private lateinit var moviesAdapter: MoviesAdapter
    private var allNewsView: View? = null
    var mContainerId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allNewsView = inflater.inflate(R.layout.fragment_movies, container, false)
        mContainerId = container?.id ?: -1
        return allNewsView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()

    }

    private fun setupUI() {

        moviesAdapter = MoviesAdapter(arrayListOf())
        recyclerView_movies.apply {

            layoutManager = GridLayoutManager(activity, 3)


            val itemDecoration = ItemOffsetDecoration(context, R.dimen.item_offset)




            addItemDecoration(


                itemDecoration
            )

            adapter = moviesAdapter
        }


    }




    private fun setupObserver() {
        moviesViewModel.movies.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar_movies.visibility = View.GONE
                    it.data?.let {
                        renderList(it)
                    }
                    recyclerView_movies.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar_movies.visibility = View.VISIBLE
                    progressBar_movies.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar_movies.visibility = View.GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(allNews: List<Movies>) {
        moviesAdapter.addData(allNews)
        moviesAdapter.notifyDataSetChanged()
    }

     fun gotoActivity(allNews: List<Movies>) {
        moviesAdapter.addData(allNews)
        moviesAdapter.notifyDataSetChanged()
    }

}