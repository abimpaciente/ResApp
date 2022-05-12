package com.example.resball.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.resball.data.database.entities.CountriesEntity

@Dao
interface CountryDao {

    @Query("SELECT * FROM countries_table")
    suspend fun getAllCountries():List<CountriesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCountries(quotes:List<CountriesEntity>)

    @Query("DELETE FROM countries_table")
    suspend fun deleteAllCountries()
}