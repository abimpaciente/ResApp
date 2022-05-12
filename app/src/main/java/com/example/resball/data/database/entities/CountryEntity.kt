package com.example.resball.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.resball.domain.model.Country
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@Entity(tableName = "countries_table")
data class CountriesEntity(

    @ColumnInfo(name = "continent")
    val continent: String?,

    @ColumnInfo(name = "country_code")
    val countryCode: String?,

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo(name = "name")
    val name: String?
)

fun Country.toDatabase() =
    CountriesEntity(continent = continent, countryCode = countryCode, id = id, name = name)