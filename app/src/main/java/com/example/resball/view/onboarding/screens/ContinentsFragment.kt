package com.example.resball.view.onboarding.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.resball.R
import com.example.resball.common.Continents
import com.example.resball.databinding.FragmentContinentsBinding

class ContinentsFragment : Fragment() {


    private val binding by lazy {
        FragmentContinentsBinding.inflate(layoutInflater)
    }
    private lateinit var recyclerContinents: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initViews()

        recyclerContinents.adapter = ContinentsAdapter(Continents.values()) {
            onClick(it)
        }

        return binding.root
    }

    private fun onClick(it: Continents) {

        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("Continent", it.valueName)
        editor.apply()

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        viewPager?.currentItem = 1
    }

    private fun initViews() {

        recyclerContinents = binding.continentsRecyclerview

        recyclerContinents.layoutManager =
            GridLayoutManager(this.context, 3, LinearLayoutManager.VERTICAL, false)

    }
}