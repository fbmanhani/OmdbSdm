package br.edu.ifsp.sdm.manhani.omdbsdm.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import br.edu.ifsp.sdm.manhani.omdbsdm.R
import br.edu.ifsp.sdm.manhani.omdbsdm.dto.Filme
import br.edu.ifsp.sdm.manhani.omdbsdm.inflate
import kotlinx.android.synthetic.main.layout_listar_filmes_adapter.view.*

class ListaFilmesAdapter : RecyclerView.Adapter<FilmeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): FilmeHolder {
        val inflatedView = parent.inflate(R.layout.layout_listar_filmes_adapter, false)
        return FilmeHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(parent: FilmeHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class FilmeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewNome = itemView.textViewNome


    fun bindView(filme: Filme) {
        textViewNome.text = filme.Title
    }
}

