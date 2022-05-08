package com.example.resball.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountriesResponse(
    @Json(name = "query")
    val query: Query?,
    @Json(name = "results")
    val results: List<Result>?
)