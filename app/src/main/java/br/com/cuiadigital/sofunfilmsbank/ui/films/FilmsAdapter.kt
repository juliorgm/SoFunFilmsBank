package br.com.cuiadigital.sofunfilmsbank.ui.films

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.cuiadigital.sofunfilmsbank.R
import br.com.cuiadigital.sofunfilmsbank.model.Film
import coil.load

class FilmsAdapter(private val listerner: FilmClickItemListener): RecyclerView.Adapter<FilmsAdapter.ViewHolder>() {

    private val listFilms= mutableListOf<Film?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_film, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = listFilms[position]
        if (film != null)
            holder.bind(film)
    }

    override fun getItemCount(): Int = listFilms.size

    fun updateListFIlms(list: List<Film>){
        listFilms.clear()
        listFilms.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) ,
    View.OnClickListener{
        val title = itemView.findViewById<TextView>(R.id.item_film_title)
        val year = itemView.findViewById<TextView>(R.id.item_film_year)
        val type = itemView.findViewById<TextView>(R.id.item_film_type)
        val poster = itemView.findViewById<ImageView>(R.id.item_film_poster)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(film : Film){
            title.text = film.title
            year.text = film.year
            type.text = film.type
            poster.load(film.poster){
                placeholder(R.drawable.ic_film_placeholder)
                fallback(R.drawable.ic_film_placeholder)
            }
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listFilms[position]?.let { listerner.clickItemFilm(it) }
            }
        }
    }

    interface FilmClickItemListener {
        fun clickItemFilm(film: Film)
    }
}



