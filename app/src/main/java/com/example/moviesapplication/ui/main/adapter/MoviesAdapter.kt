package com.example.Sourceapp.ui.main.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapplication.R
import com.example.moviesapplication.data.local.db.entities.Movies
import com.example.moviesapplication.ui.main.view.MoviesDetailActivity
import kotlinx.android.synthetic.main.item_movies_layout.view.*
import javax.inject.Inject


class MoviesAdapter @Inject constructor(


    private val movies: ArrayList<Movies>

) : RecyclerView.Adapter<MoviesAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(movies: Movies) {

          itemView.tv_title.text= movies.title


            Glide.with(itemView.iv_movie_poster.context)
                .load("https://image.tmdb.org/t/p/w500${movies.poster_path}")
                .into(itemView.iv_movie_poster)


            itemView.setOnClickListener {

                val intent = Intent(it.context,MoviesDetailActivity::class.java)
                intent.putExtra("MOVIE_ID",movies.id.toString())



              it.context.startActivity(intent)
            }



        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movies_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(movies[position])


    fun addData(list: List<Movies>) {
        movies.clear()
        movies.addAll(list)
    }


}