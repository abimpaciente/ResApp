package com.example.resball.repository

import com.example.resball.api.RetrofitInstance
import com.example.resball.model.CountriesResponse
import retrofit2.Response

class Repository {
    suspend fun getCountries(): Response<CountriesResponse> {
        return RetrofitInstance.api.getCountries()
    }

}