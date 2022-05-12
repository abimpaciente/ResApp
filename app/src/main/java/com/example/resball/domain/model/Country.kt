package com.example.resball.domain.model

import com.example.resball.data.database.entities.CountriesEntity
import com.example.resball.data.model.CountryModel

data class Country(val continent: String?, val countryCode: String?, val id: Int?, val name: String?)

fun CountryModel.toDomain() = Country(continent, countryCode, id, name)
fun CountriesEntity.toDomain() = Country(continent, countryCode, id, name)
