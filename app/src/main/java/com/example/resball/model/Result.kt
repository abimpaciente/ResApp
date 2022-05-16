package com.example.resball.model


import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "continent")
    val continent: String?,
    @SerializedName("country_code")
    @Json(name = "country_code")
    val countryCode: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
)