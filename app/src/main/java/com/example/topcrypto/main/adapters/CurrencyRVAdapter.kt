package com.example.topcrypto.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.topcrypto.databinding.CurrencyRvItemBinding
import com.example.topcrypto.main.model.CurrencyModal

class CurrencyRVAdapter: ListAdapter<CurrencyModal, CurrencyRVAdapter.CurrencyViewHolder>(CurrencyModelDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = CurrencyRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = getItem(position)
        holder.bind(currency)
    }

    class CurrencyViewHolder(private val binding: CurrencyRvItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: CurrencyModal) {
            binding.apply {
                idTVName.text = currency.name
                idTVSymbol.text = currency.symbol
                idTVRate.text = currency.price.toString()
            }
        }
    }
}