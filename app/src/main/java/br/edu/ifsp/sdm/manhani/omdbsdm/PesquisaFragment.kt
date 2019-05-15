package br.edu.ifsp.sdm.manhani.omdbsdm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifsp.sdm.manhani.omdbsdm.adapter.ListaFilmesAdapter
import br.edu.ifsp.sdm.manhani.omdbsdm.dto.Filme


class PesquisaFragment : Fragment() {

    lateinit var filmeAdapter: ListaFilmesAdapter
//    private var filmes = arrayOf(MutableList<Filme>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        filmeAdapter
        return inflater.inflate(R.layout.fragment_pesquisa, container, false)
    }

}
