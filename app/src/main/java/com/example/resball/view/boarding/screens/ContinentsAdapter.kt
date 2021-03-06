package com.example.resball.view.boarding.screens

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.resball.common.Continents
import com.example.resball.databinding.ContinentAdapterBinding
import com.squareup.picasso.Picasso

class ContinentsAdapter(
    private val dataSet: Array<Continents>,
    private val selectedContinent: String,
    private val onClick: (Continents) -> Unit
) : RecyclerView.Adapter<ContinentsAdapter.ContinentsHolder>() {


    class ContinentsHolder(private val binding: ContinentAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            continents: Continents,
            onSelectImage: (Continents) -> Unit,
        ) {
            binding.tvContinent.text = continents.valueName
            Picasso.get().load(continents.urlImg)
                .resize(180, 180)
                .centerCrop().into(binding.ivContinent)
            binding.cvContinent.setOnClickListener {
                onSelectImage(continents)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContinentsHolder {
        val binding =
            ContinentAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ContinentsHolder(binding)
    }

    override fun onBindViewHolder(holder: ContinentsHolder, position: Int) {
        holder.onBind(dataSet[position], onClick)

        if (selectedContinent != dataSet[position].valueName) {
            holder.itemView.setBackgroundColor(Color.WHITE)
        } else {
            holder.itemView.setBackgroundColor(Color.BLACK)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}