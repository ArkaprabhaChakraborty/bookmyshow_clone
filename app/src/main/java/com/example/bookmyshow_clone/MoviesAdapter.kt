package com.example.bookmyshow_clone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_item_card.view.*


class MoviesAdapter(private val movies:List<Movie>):RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>()
{
    override fun getItemCount(): Int = movies.count()

    class MoviesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        companion object
        {
            private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        }
        fun bind(movie:Movie)
        {
            itemView.movie_name.text = movie.title
            itemView.release_date.text = movie.releaseDate
            itemView.avg_rating.text = movie.voteAverage.toString()
            itemView.total_votes.text = movie.voteCount.toString()
            Glide.with(itemView.context).load(IMAGE_BASE_URL + movie.posterPath).into(itemView.movie_poster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_card,parent,false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movies[position])
    }


}