package com.example.resball.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.resball.R
import com.example.resball.databinding.FragmentHomeBinding
import com.example.resball.view.onboarding.SplashFragment
import com.example.resball.view.onboarding.screens.ContinentsFragment
import com.example.resball.view.sports.LeaguesFragment
import com.example.resball.view.sports.SettingsFragment
import com.example.resball.view.sports.TeamsFragment
import com.example.resball.view.sports.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setupTabs()


        return binding.root
    }

    private fun setupTabs() {

        val fragmentList = arrayListOf<Fragment>(
            TeamsFragment(),
            LeaguesFragment(),
            SettingsFragment()
        )

        val titlesList = arrayListOf<String>(
            "Teams",
            "Leagues",
            "Settings"
        )


        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPagerPrincipal.adapter = adapter

        TabLayoutMediator(binding.tabsSport, binding.viewPagerPrincipal) { tab, position ->
            tab.text = titlesList[position]
            tab.customView?.isSelected
        }.attach()

        binding.tabsSport.getTabAt(0)?.setIcon(R.drawable.ic_baseline_home_24)
        binding.tabsSport.getTabAt(1)?.setIcon(R.drawable.ic_baseline_favorite_24)
        binding.tabsSport.getTabAt(2)?.setIcon(R.drawable.ic_baseline_settings_24)



    }

}