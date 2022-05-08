package com.example.resball.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "continent")
    val continent: String?,
    @Json(name = "country_code")
    val countryCode: Any?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
)