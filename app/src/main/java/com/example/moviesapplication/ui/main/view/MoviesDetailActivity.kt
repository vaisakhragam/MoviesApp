package com.example.moviesapplication.ui.main.view
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.moviesapplication.R
import com.example.moviesapplication.data.local.db.entities.Movies
import com.example.moviesapplication.ui.main.viewmodel.MoviesDetailViewModel
import com.example.moviesapplication.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*

@AndroidEntryPoint
class MoviesDetailActivity : AppCompatActivity() {

    private val moviesDetailViewModel: MoviesDetailViewModel by viewModels()

    private lateinit var movie_id:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        movie_id = intent.getStringExtra("MOVIE_ID")

       System.out.println("movie_d=$movie_id")
        setupUI()
        setupObserver()

    }



    private fun setupUI() {


        fab.setOnClickListener {
            moviesDetailViewModel.onFavoriteClicked()

            if (moviesDetailViewModel.isFavorite()) {


                fab.setImageResource(R.drawable.favorite)
            } else {
                fab.setImageResource(R.drawable.favorite_border)
            }
        }




    }

    private fun setupObserver() {


        moviesDetailViewModel.setMovieID(movie_id)


        moviesDetailViewModel.movie.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    //progressBar.visibility = View.GONE
                    it.data?.let {
                        renderList(it)
                    }
                    //  recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    //  progressBar.visibility = View.VISIBLE
                    //  recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    //  progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        moviesDetailViewModel.isfav.observe(this, Observer {

            if (it) {


                fab.setImageResource(R.drawable.favorite)
            } else {
            fab.setImageResource(R.drawable.favorite_border)
        }

        }

        )
    }

    private fun renderList(movie: Movies) {
        tv_title.text = movie.title
        tv_content.text = movie.overview

        Glide.with(iv_backdrop.context)
            .load("https://image.tmdb.org/t/p/w500${movie.backdrop_path}")
            .into(iv_backdrop)}

}
