package br.edu.ifsp.sdm.manhani.omdbsdm

import br.edu.ifsp.sdm.manhani.omdbsdm.dto.Retorno
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {
    @GET("/") //summary: Returns the most popular match for a given title
    fun getFilmes(@Query("s") titulo: String): Call<Retorno>
}