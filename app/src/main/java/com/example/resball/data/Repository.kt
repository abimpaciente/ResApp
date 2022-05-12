package com.example.resball.data

import com.example.resball.data.database.dao.CountryDao
import com.example.resball.data.database.entities.CountriesEntity
import com.example.resball.data.model.CountryModel
import com.example.resball.data.network.RequestService
import com.example.resball.domain.model.Country
import com.example.resball.domain.model.toDomain
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: RequestService,
    private val dao: CountryDao
) {

    suspend fun getAllCountriesFromApi(continent: String): List<Country> {
        val response: List<CountryModel> = api.getCountriesByContinent(continent)
        return response.map { it.toDomain() }
    }

    suspend fun getAllCountriesFromDatabase(): List<Country> {
        val response: List<CountriesEntity> = dao.getAllCountries()
        return response.map { it.toDomain() }
    }

    suspend fun insertCountries(quotes: List<CountriesEntity>) {
        dao.insertAllCountries(quotes)
    }

    suspend fun clearCountries() {
        dao.deleteAllCountries()
    }

}

/*

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao
) {

    suspend fun getAllQuotesFromApi(): List<Quote> {
        val response: List<QuoteModel> = api.getQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase():List<Quote>{
        val response: List<QuoteEntity> = quoteDao.getAllQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes:List<QuoteEntity>){
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuotes(){
        quoteDao.deleteAllQuotes()
    }
}*/