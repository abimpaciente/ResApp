package com.example.resball.util

sealed class UIState {
    object Loading : UIState()
    data class Success<T>(val response: T) : UIState()
    data class Error(val error: Exception) : UIState()
}

sealed class ViewIntentRequest(val apiKey: String, val continents: String? = null) {

    data class GetCountriesByContinent(val api: String, val continent: Continents) :
        ViewIntentRequest(api, continent.valueName)

    data class GetCountries(val api: String) : ViewIntentRequest(api)
}