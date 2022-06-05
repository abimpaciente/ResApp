package com.example.resball.view.sports

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.resball.R
import com.example.resball.common.Constants
import com.example.resball.common.Continents
import com.example.resball.databinding.FragmentSettingsBinding
import com.example.resball.model.Country
import com.google.gson.Gson
import com.squareup.picasso.Picasso


class SettingsFragment : Fragment() {

    private val binding by lazy {
        FragmentSettingsBinding.inflate(layoutInflater)
    }
    private lateinit var selectedContinent: String
    private lateinit var selectedCountry: Country

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initViews()

        binding.tvContinents.text = selectedContinent
        binding.tvCountries.text = selectedCountry.name

        val continent = Continents.valueOf(selectedContinent.replace(" ", ""))

        var urlImage = continent.urlImg
        filImage(urlImage, binding.ivContinent)

        urlImage = Constants.BASE_URL_FLAG + selectedCountry.countryCode
        filImage(urlImage, binding.ivCountry)

        binding.imgSettings.setOnClickListener {
            val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
            viewPager?.currentItem = 0
            onBoardingFinished()
        }

        return binding.root
    }

    private fun filImage(urlImage: String, imageView: ImageView) {
        Picasso.get().load(urlImage)
            .resize(180, 180)
            .centerCrop().into(imageView)
    }

    private fun initViews() {

        val mPrefs: SharedPreferences =
            requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)

        val data = mPrefs.getString("Continent", null)
        selectedContinent = data.toString()
        val gson = Gson()
        val json = mPrefs.getString("Country", null)
        val country: Country = gson.fromJson(json, Country::class.java)
        selectedCountry = country

    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", false)
        editor.apply()
        findNavController().navigate(R.id.action_homeFragment_to_splashFragment)

    }

}