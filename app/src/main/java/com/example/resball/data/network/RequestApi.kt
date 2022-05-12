package com.example.resball.data.network

import com.example.resball.common.Continents
import com.example.resball.common.END_POINT_COUNTRIES
import com.example.resball.common.PARAM_API_KEY
import com.example.resball.common.PARAM_CONTINENTS
import com.example.resball.data.model.CountriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestApi {

    @GET(END_POINT_COUNTRIES)
    fun getCountriesByContinents(
        @Query(PARAM_API_KEY) apyKey: String,
        @Query(PARAM_CONTINENTS) continent: String,
    ): Response<CountriesResponse>
}

