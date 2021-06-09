package br.com.cuiadigital.sofunfilmsbank.ui.favorite_films

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.cuiadigital.sofunfilmsbank.R
import br.com.cuiadigital.sofunfilmsbank.model.Film
import br.com.cuiadigital.sofunfilmsbank.model.FilmDetail
import coil.load

class FilmsFavoritesAdapter(private val listerner: FilmFavoriteClickItemListener): RecyclerView.Adapter<FilmsFavoritesAdapter.ViewHolder>() {

    private val listFilms= mutableListOf<FilmDetail?>()

    companion object{
        val ZERO_STAR = 0.0F
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_film, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = listFilms[position]
        holder.bind(film)
    }

    override fun getItemCount(): Int = listFilms.size

    fun updateListFIlms(list: List<FilmDetail?>){
        listFilms.clear()
        listFilms.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) ,
    View.OnClickListener{
        val title = itemView.findViewById<TextView>(R.id.favorite_item_title)
        val plot = itemView.findViewById<TextView>(R.id.favorite_item_plot)
        val rating = itemView.findViewById<RatingBar>(R.id.favorite_item_rating)
        val poster = itemView.findViewById<ImageView>(R.id.favorite_item_poster)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(film : FilmDetail?){
            title.text = film?.title
            plot.text = film?.plot
            rating.rating = film?.imdbRating?.toFloat() ?: ZERO_STAR
            poster.load(film?.poster){
                placeholder(R.drawable.ic_film_placeholder)
                fallback(R.drawable.ic_film_placeholder)
            }
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listFilms[position]?.let { listerner.clickItemFavoriteFilm(it) }
            }
        }
    }

    interface FilmFavoriteClickItemListener {
        fun clickItemFavoriteFilm(film: FilmDetail?)
    }
}



