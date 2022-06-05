package com.example.resball.api

import com.example.resball.common.Constants.Companion.API_KEY
import com.example.resball.common.Constants.Companion.END_POINT_COUNTRIES
import com.example.resball.common.Constants.Companion.PARAM_API_KEY
import com.example.resball.common.Constants.Companion.PARAM_CONTINENT
import com.example.resball.model.CountriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestApi {
    @GET(END_POINT_COUNTRIES)
    suspend fun getCountries(
        @Query(PARAM_API_KEY) apikey: String = API_KEY,
    ): Response<CountriesResponse>

}