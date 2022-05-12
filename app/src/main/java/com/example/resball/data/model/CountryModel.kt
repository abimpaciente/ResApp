package com.example.resball.data.model


import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryModel(
    @Json(name = "continent")
    @SerializedName("continent")
    val continent: String?,
    @Json(name = "country_code")
    @SerializedName("country_code")
    val countryCode: String?,
    @Json(name = "id")
    @SerializedName("id")
    val id: Int?,
    @Json(name = "name")
    @SerializedName("name")
    val name: String?
)