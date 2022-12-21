package com.example.topcrypto.main.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topcrypto.main.model.CurrencyModal
import com.example.topcrypto.main.model.data.CryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    @Inject
    lateinit var repository: CryptoRepository

    private var _currencies = MutableLiveData<List<CurrencyModal>>()
    val currencies: LiveData<List<CurrencyModal>> get() = _currencies

    fun getCurrencies() {
        viewModelScope.launch {
            repository.getCurrencies().collect {
                _currencies.postValue(it)
            }
        }
    }
}