package br.edu.ifsp.sdm.manhani.omdbsdm

import br.edu.ifsp.sdm.manhani.omdbsdm.dto.RetornoDetalhe
import br.edu.ifsp.sdm.manhani.omdbsdm.dto.RetornoPesquisa
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {
    @GET("/")
    fun getFilmes(@Query("s") titulo: String): Call<RetornoPesquisa>

    @GET("/")
    fun getFilme(@Query("i") id: String): Call<RetornoDetalhe>
}