package br.edu.ifsp.sdm.manhani.omdbsdm.dto

import com.google.gson.annotations.SerializedName

class RetornoPesquisa {

    @SerializedName("Response")
    val response: String? = null

    @SerializedName("Search")
    val filmes: List<Filme> = arrayListOf()

    val totalResults: String? = null
}