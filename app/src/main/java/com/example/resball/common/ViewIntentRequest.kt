package com.example.resball.common


sealed class ViewIntentRequest(val apiKey: String, val continents: String? = null) {

    data class GetCountriesByContinent(val api: String, val continent: Continents) :
        ViewIntentRequest(api, continent.valueName)

    data class GetCountries(val api: String) : ViewIntentRequest(api)
}