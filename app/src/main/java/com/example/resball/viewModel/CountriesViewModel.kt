package com.example.resball.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resball.model.CountriesResponse
import com.example.resball.model.Result
import com.example.resball.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class CountriesViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<Response<CountriesResponse>> = MutableLiveData()
    val listCountries: MutableLiveData<List<Result>> = MutableLiveData()

    fun getCountries() {
        viewModelScope.launch {
//            withContext(Dispatchers.IO) {

            val response = repository.getCountries()
            myResponse.value = response
        }
//        }
    }

    fun getCountriesByContinent(continent: String) {
        Log.d(TAG, "getCountriesByContinent: $continent")
        listCountries.value = myResponse.value?.body()?.data?.filter { x ->
            x.continent == continent
        } ?: emptyList()
    }
}