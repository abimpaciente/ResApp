package com.example.resball.view.onboarding.screens

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.resball.R
import com.example.resball.databinding.FragmentCountriesBinding
import com.example.resball.model.Result
import com.example.resball.repository.Repository
import com.example.resball.viewModel.CountriesViewModel
import com.example.resball.viewModel.CountriesViewModelFactory
import androidx.navigation.fragment.findNavController

class CountriesFragment : Fragment() {

    private val binding by lazy {
        FragmentCountriesBinding.inflate(layoutInflater)
    }

    private lateinit var selectedContinent: String
    private lateinit var selectedCountry: String

    //    private lateinit var recyclerCountries: RecyclerView
    private lateinit var adapter: CountryAdapter

    private lateinit var viewModel: CountriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initViews()


        viewModel.getCountries()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                val result = response.body()?.data
                result?.filter { x ->
                    x.continent == selectedContinent
                }?.let { updateAdapter(it) }
            }
        })

        viewModel.listCountries.observe(viewLifecycleOwner) {
            updateAdapter(it)
        }

        return binding.root
    }

    private fun initViews() {

        binding.countriesRecyclerview.layoutManager = LinearLayoutManager(this.context)

        getSelectedContinent()

        val repository = Repository()

        val viewModelFactory = CountriesViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[CountriesViewModel::class.java]


        val mPrefs: SharedPreferences =
            requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)

        val data = mPrefs.getString("Country", null)

        selectedCountry = data.toString()
    }

    private fun getSelectedContinent() {
        val mPrefs: SharedPreferences =
            requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)

        val data = mPrefs.getString("Continent", null)

        selectedContinent = data.toString()
    }

    private fun updateAdapter(results: List<Result>) {


        Log.d(TAG, "updateAdapter: $selectedContinent")
        adapter = CountryAdapter(results, selectedCountry) {
            onClick(it)
        }

        Log.d(TAG, "updateAdapter: $results")

        binding.countriesRecyclerview.adapter = adapter

    }

    private fun onClick(country: Result) {
        selectedCountry = country.name.toString()
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("Country", selectedCountry)
        Log.d("Country", "onClick: $selectedCountry")
        editor.apply()

        onBoardingFinished()
        findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment)
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        getSelectedContinent()
        viewModel.getCountries()
    }
}