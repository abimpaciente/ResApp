package com.example.resball.view.boarding.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.resball.common.Constants.Companion.BASE_URL_FLAG
import com.example.resball.databinding.CountryAdapterBinding
import com.example.resball.model.Country
import com.squareup.picasso.Picasso

class CountryAdapter(
    private val dataSet: List<Country>,
    private val onClick: (Country) -> Unit
) : RecyclerView.Adapter<CountryAdapter.CountryHolder>() {

    class CountryHolder(private val binding: CountryAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            countries: Country,
            onClick: (Country) -> Unit
        ) {
            val country = countries.name
            val urlImage = BASE_URL_FLAG + countries.countryCode
            Picasso.get().load(urlImage)
                .resize(180, 180)
                .centerCrop().into(binding.ivCountry)
            binding.tvCountry.text = country
            binding.cvCountry.setOnClickListener {
                onClick(countries)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val binding =
            CountryAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CountryHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.onBind(dataSet[position], onClick)

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}