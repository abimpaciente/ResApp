package com.example.resball.repository

import com.example.resball.api.RetrofitInstance
import com.example.resball.model.CountriesResponse
import retrofit2.Response

class Repository {
    suspend fun getCountries(): Response<CountriesResponse> {
        return RetrofitInstance.api.getCountries()
    }
    suspend fun getCountriesByContinent(continent: String): Response<CountriesResponse> {
        return RetrofitInstance.api.getCountriesByContinent(continent)
    }
}