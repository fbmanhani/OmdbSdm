package br.edu.ifsp.sdm.manhani.omdbsdm.dto

import com.google.gson.annotations.SerializedName

data class Filme(
    @SerializedName("Poster")
    val poster: String?,

    @SerializedName("Title")
    val title: String?,

    @SerializedName("Type")
    val type: String?,

    @SerializedName("Year")
    val year: String?,

    val imdbID: String?
)