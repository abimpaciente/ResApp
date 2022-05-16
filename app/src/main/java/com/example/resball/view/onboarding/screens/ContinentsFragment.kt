package com.example.resball.view.onboarding.screens

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.resball.R
import com.example.resball.common.Continents
import com.example.resball.databinding.FragmentContinentsBinding
import com.example.resball.repository.Repository
import kotlin.math.log


class ContinentsFragment : Fragment() {

    private lateinit var selectedContinent: String

    private val binding by lazy {
        FragmentContinentsBinding.inflate(layoutInflater)
    }
    private lateinit var recyclerContinents: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initViews()

        recyclerContinents.adapter = ContinentsAdapter(Continents.values(), selectedContinent) {
            onClick(it)
        }
        return binding.root
    }

    private fun onClick(continent: Continents) {

//        recyclerContinents.adapter?.bindViewHolder(viewHolder, 5)
        selectedContinent = continent.valueName
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("Continent", selectedContinent)
        editor.apply()
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        viewPager?.currentItem = 1
    }

    override fun onPause() {
        super.onPause()
        recyclerContinents.adapter = ContinentsAdapter(Continents.values(), selectedContinent) {
            onClick(it)
        }
    }


    private fun initViews() {


        recyclerContinents = binding.continentsRecyclerview

        recyclerContinents.layoutManager =
            GridLayoutManager(this.context, 3, LinearLayoutManager.VERTICAL, false)

        val mPrefs: SharedPreferences =
            requireActivity().getSharedPreferences("onBoarding", MODE_PRIVATE)

        val data = mPrefs.getString("Continent", null)

        selectedContinent = data.toString()

    }
}