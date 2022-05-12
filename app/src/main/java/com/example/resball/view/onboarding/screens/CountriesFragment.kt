package com.example.resball.view.onboarding.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.resball.common.Continents
import com.example.resball.databinding.FragmentCountriesBinding
import com.example.resball.viewmodel.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesFragment : Fragment() {

    private val binding by lazy {
        FragmentCountriesBinding.inflate(layoutInflater)
    }

    /**/
    private val countryViewModel: CountryViewModel by viewModels()
//    private val countryViewModel = ViewModelProvider(this)[CountryViewModel::class.java]


//    private val countryViewModel: CountryViewModel by lazy {
//        ViewModelProvider(this,
//            object : ViewModelProvider.Factory {
//                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//                    return CountryViewModel(repository) as T
//                }
//            })[CountryViewModel::class.java]
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val sharedPref =
            requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val continentSaved = sharedPref.getString("Continent", null)
        if (continentSaved != null)
            initObserve(continentSaved)

//        binding.btnFinish.setOnClickListener {
//            findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment)
//            onBoardingFinished()
//        }
        return binding.root
    }

    private fun initObserve(continentSaved: String) {
        with(countryViewModel) {
            getCountry(continentSaved)

            quoteModel.observe(viewLifecycleOwner, Observer {
            })
            isLoading.observe(viewLifecycleOwner, Observer {
            })
        }
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

}