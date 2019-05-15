package br.edu.ifsp.sdm.manhani.omdbsdm.dto

data class Retorno(
    val Response: String?,
    val Filme: List<Filme?>?,
    val totalResults: String?
)