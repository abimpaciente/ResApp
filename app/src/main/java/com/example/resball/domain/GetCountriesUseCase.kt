package com.example.resball.domain

import com.example.resball.data.Repository
import com.example.resball.data.database.entities.toDatabase
import com.example.resball.domain.model.Country
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(continent: String): List<Country> {
        val countries = repository.getAllCountriesFromApi(continent)
        return if (countries.isNotEmpty()) {
            repository.clearCountries()
            repository.insertCountries(countries.map { it.toDatabase() })
            countries
        } else {
            repository.getAllCountriesFromDatabase()
        }
    }
}
