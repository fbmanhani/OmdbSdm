package br.edu.ifsp.sdm.manhani.omdbsdm.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import br.edu.ifsp.sdm.manhani.omdbsdm.R
import br.edu.ifsp.sdm.manhani.omdbsdm.dto.Filme
import br.edu.ifsp.sdm.manhani.omdbsdm.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_listar_filmes_adapter.view.*

class ListaFilmesAdapter(private val lista: List<Filme>) : RecyclerView.Adapter<FilmeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): FilmeHolder {
        val inflatedView = parent.inflate(R.layout.layout_listar_filmes_adapter, false)
        return FilmeHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(parent: FilmeHolder, position: Int) {
        val filme: Filme = lista[position]
        parent.bindView(filme)
    }

}

class FilmeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageViewCapa = itemView.imageViewCapa
    val textViewNome = itemView.textViewNome
    val textViewTipo = itemView.textViewTipo
    val textViewAno = itemView.textViewAno


    fun bindView(filme: Filme) {
        textViewNome.text = filme.title
        textViewTipo.text = filme.type
        textViewAno.text = filme.year
        if(filme.poster?.isNotBlank()!!) {
            Picasso.get().load(filme.poster).into(imageViewCapa)
        }
    }
}

