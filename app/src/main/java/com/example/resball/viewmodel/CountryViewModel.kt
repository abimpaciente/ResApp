package com.example.resball.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resball.domain.GetCountriesUseCase
import com.example.resball.domain.model.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val getCountries: GetCountriesUseCase
) : ViewModel() {
    val quoteModel = MutableLiveData<List<Country>>()
    val isLoading = MutableLiveData<Boolean>()

    fun getCountry(continent: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getCountries.invoke(continent)

            if (!result.isNullOrEmpty()) {
                quoteModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
}

/*
@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
) : ViewModel() {

    val quoteModel = MutableLiveData<Quote>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuotesUseCase()

            if (!result.isNullOrEmpty()) {
                quoteModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    fun randomQuote() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val quote = getRandomQuoteUseCase()
            if (quote != null) {
                quoteModel.postValue(quote)
            }
            isLoading.postValue(false)
        }
    }
}*/