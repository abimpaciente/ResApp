package com.example.resball.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resball.rest.Repository
import com.example.resball.util.UIState
import com.example.resball.util.UIState.Success
import com.example.resball.util.ViewIntentRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountriesViewModel @Inject constructor(
    private val repository: Repository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _loading: MutableLiveData<UIState> = MutableLiveData(UIState.Loading)
    val loading: LiveData<UIState>
        get() = _loading

//    private val _listData: MutableLiveData<UIState> = MutableLiveData(UIState.Success(it))
//    val listData: LiveData<UIState>
//        get() = _listData


    fun getListCountries(actionIntent: ViewIntentRequest) {
        when (actionIntent) {
            is ViewIntentRequest.GetCountriesByContinent -> {
                collectCountriesByContinent(actionIntent)
            }
            is ViewIntentRequest.GetCountries -> {

            }
            else -> {

            }
        }
    }

    private fun collectCountriesByContinent(actionIntent: ViewIntentRequest.GetCountriesByContinent) {
        viewModelScope.launch(ioDispatcher) {
            repository.getCountriesByContinent(actionIntent).collect {
//                _listData.postValue(it)
                _loading.postValue(it)
            }
        }

    }


}