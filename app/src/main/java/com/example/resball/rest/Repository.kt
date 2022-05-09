package com.example.resball.rest

import com.example.resball.util.UIState
import com.example.resball.util.ViewIntentRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

interface Repository {
    fun getCountriesByContinent(viewIntentRequest: ViewIntentRequest): Flow<UIState>

    fun getCountries(viewIntentRequest: ViewIntentRequest): Flow<UIState>
}

class RepositoryImpl @Inject constructor(private val serveApi: RequestApi) : Repository {
    override fun getCountriesByContinent(request: ViewIntentRequest): Flow<UIState> =
        flow {
            emit(UIState.Loading)

            try {
                val response =
                    request.continents?.let {
                        serveApi.getListCountriesByContinent(
                            request.apiKey,
                            it
                        )
                    }
                response?.let {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            emit(UIState.Success(it))
                        } ?: throw Exception("Error null response")
                    } else {
                        throw Exception("Failure response")
                    }

                }
            } catch (e: Exception) {
//                emit(UIState.Error(e))
            }

        }

    override fun getCountries(viewIntentRequest: ViewIntentRequest): Flow<UIState> {
        TODO("Not yet implemented")
    }

}