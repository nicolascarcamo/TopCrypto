package com.example.topcrypto.main.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.topcrypto.main.model.CurrencyModal

class CurrencyModelDiffCallback: DiffUtil.ItemCallback<CurrencyModal>() {
    override fun areItemsTheSame(oldItem: CurrencyModal, newItem: CurrencyModal): Boolean {
        return oldItem.symbol == newItem.symbol
    }

    override fun areContentsTheSame(oldItem: CurrencyModal, newItem: CurrencyModal): Boolean {
        return oldItem == newItem
    }

}