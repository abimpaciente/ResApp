package com.example.resball.rest

import com.example.resball.model.CountriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RequestApi {
    @GET(COUNTRIES)
    suspend fun getListCountriesByContinent(
        @Query(PARAM_API) apikey: String,
        @Query(PARAM_COUNTRY) continent: String
    ): Response<CountriesResponse>

//    @GET(RANDOM_CAT_EXCUSE)
//    suspend fun getListRandomExcuseByCategory(
//        @Path("excuseNumber") excuseNumber: Int,
//        @Path("category") category: String?
//    ): Response<List<ExcusesItem>>

    companion object {
        //https://excuser.herokuapp.com/v1/excuse/3
        const val BASE_URL = "https://app.sportdataapi.com/api/v1/soccer/"
        private const val COUNTRIES = "{countries}"
        private const val RANDOM_LIST_EXCUSE = "{excuseNumber}"
        private const val RANDOM_CAT_EXCUSE = "{category}/{excuseNumber}"
        private const val PARAM_COUNTRY = "continent"
        private const val PARAM_API = "continent"

    }
}