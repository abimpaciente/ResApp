package com.example.resball.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resball.model.CountriesResponse
import com.example.resball.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class CountriesViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<Response<CountriesResponse>> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()

    fun getCountries() {
        isLoading.postValue(true)
        viewModelScope.launch {
            val response = repository.getCountries()
            myResponse.value = response
            isLoading.postValue(false)
        }
    }

}