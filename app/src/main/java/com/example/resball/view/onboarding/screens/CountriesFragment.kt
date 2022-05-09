package com.example.resball.view.onboarding.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.resball.R
import com.example.resball.databinding.FragmentCountriesBinding
import com.example.resball.rest.Repository
import com.example.resball.util.Continents
import com.example.resball.util.ViewIntentRequest
import com.example.resball.viewModel.CountriesViewModel
import javax.inject.Inject

class CountriesFragment : Fragment() {

    private val binding by lazy {
        FragmentCountriesBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var repository: Repository


//    private val viewModel: CountriesViewModel by lazy {
//        ViewModelProvider(this).get(CountriesViewModel::class.java)
//    }


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private val viewModel: CountriesViewModel by lazy {
        ViewModelProvider(this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return CountriesViewModel(repository) as T
                }
            })[CountriesViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initObservable()
//        binding.btnFinish.setOnClickListener {
//            findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment)
//            onBoardingFinished()
//        }
        return binding.root
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }


    fun initObservable() {
//        viewModel.loading.observe(viewLifecycleOwner) {
////            binding.lyRecyclers.isRefreshing = it
//        }
//        viewModel.listCountries.observe(this) {
//            updateUI(it)
//        }
//        viewModel.error.observe(this) {
//            val json = JSONObject(it)
//            val message = json.getString("message")
//            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
//        }
//        binding.lyRecyclers.setOnRefreshListener {
//            viewModel.getWeather(
//                requestWeather
//            )
//        }

        val intentView = ViewIntentRequest.GetCountriesByContinent("", Continents.Africa)
        val asd = viewModel.getListCountries(
            intentView
        )

    }

    /*


    @Inject
    lateinit var repository: Repository

    private val viewModel: NYCViewModel by lazy {
        ViewModelProvider(this,
        object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return NYCViewModel(repository) as T
            }
        })[NYCViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NycApp.component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = ListSchoolFragmentLayoutBinding
            .inflate(inflater, container, false)

        return binding.root
    }



    * */

}