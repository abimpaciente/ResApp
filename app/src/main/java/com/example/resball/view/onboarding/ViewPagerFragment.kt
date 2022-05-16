package com.example.resball.view.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.resball.databinding.FragmentViewPagerBinding
import com.example.resball.view.onboarding.screens.ContinentsFragment
import com.example.resball.view.onboarding.screens.CountriesFragment


class ViewPagerFragment : Fragment() {


    private val binding by lazy {
        FragmentViewPagerBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmentList = arrayListOf<Fragment>(
            ContinentsFragment(),
            CountriesFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            this.childFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter


        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 1) {

                    val sharedPref =
                        requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
                    val continentSaved = sharedPref.getString("Continent", null)

                    if (continentSaved == null) {
                        binding.viewPager.currentItem = 0
                    }

                }
            }
        })
        return binding.root
    }
}