package com.example.resball.data.network

import com.example.resball.data.model.CountryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RequestService
@Inject constructor
    (
    private val api: RequestApi
) {
//    val api:RequestApi
    suspend fun getCountriesByContinent(continent: String): List<CountryModel> {
        return withContext(Dispatchers.IO) {
            val response =
                api.getCountriesByContinents("3dd473e0-ccb4-11ec-ba08-9543770f3367", continent = continent)
            response.body()?.results ?: emptyList()
        }
    }
}