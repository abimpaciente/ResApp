package com.example.resball.view.sports

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.resball.R
import com.example.resball.common.Constants
import com.example.resball.databinding.FragmentSettingsBinding
import com.squareup.picasso.Picasso
import com.example.resball.common.Continents

class SettingsFragment : Fragment() {

    private val binding by lazy {
        FragmentSettingsBinding.inflate(layoutInflater)
    }
    private lateinit var selectedContinent: String
    private lateinit var selectedCountry: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initViews()

        binding.tvContinents.text = selectedContinent
        binding.tvCountries.text = selectedCountry

      val continent =  Continents.valueOf(selectedContinent.replace(" " , ""))

        var urlImage = continent.urlImg
        Picasso.get().load(urlImage)
            .resize(180, 180)
            .centerCrop().into(binding.ivContinent)
        urlImage = Constants.BASE_URL_FLAG + selectedCountry.replace(" ", "")
        Picasso.get().load(urlImage)
            .resize(180, 180)
            .centerCrop().into(binding.ivCountry)

        binding.btnContinents.setOnClickListener {
            val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
            viewPager?.currentItem = 0
            onBoardingFinished()
        }

        return binding.root
    }

    private fun initViews() {

        val mPrefs: SharedPreferences =
            requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)

        var data = mPrefs.getString("Continent", null)
        selectedContinent = data.toString()
        data = mPrefs.getString("Country", null)
        selectedCountry = data.toString()

    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", false)
        editor.apply()
        findNavController().navigate(R.id.action_homeFragment_to_splashFragment)

    }

}