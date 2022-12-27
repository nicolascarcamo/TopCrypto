package com.example.topcrypto.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topcrypto.databinding.ActivityMainBinding
import com.example.topcrypto.main.adapters.CurrencyRVAdapter
import com.example.topcrypto.main.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var currencyRVAdapter: CurrencyRVAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        getCurrencies()
        observeViewModel()
    }

    private fun setAdapter() {
        currencyRVAdapter = CurrencyRVAdapter()
        binding.idRVcurrency.layoutManager = LinearLayoutManager(this)
        binding.idRVcurrency.adapter = currencyRVAdapter
    }

    private fun getCurrencies() {
        viewModel.getCurrencies()
    }

    private fun observeViewModel() {
        viewModel.currencies.observe(this) { currencies ->
            currencyRVAdapter.submitList(currencies)
        }
    }
}