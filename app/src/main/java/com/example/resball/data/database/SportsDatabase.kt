package com.example.resball.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.resball.data.database.dao.CountryDao
import com.example.resball.data.database.entities.CountriesEntity

@Database(entities = [CountriesEntity::class], version = 1)
abstract class SportsDatabase : RoomDatabase() {
    abstract fun getCountriesDao(): CountryDao
}

/*
@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDatabase: RoomDatabase() {

    abstract fun getQuoteDao():QuoteDao
}*/