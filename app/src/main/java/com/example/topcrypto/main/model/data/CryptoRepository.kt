package com.example.topcrypto.main.model.data

import android.content.Context
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.topcrypto.main.model.CurrencyModal
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class CryptoRepository @Inject constructor(@ApplicationContext val context: Context) {

    private fun filter(list: ArrayList<CurrencyModal>): ArrayList<CurrencyModal> {
        list.sortWith { p1: CurrencyModal, p2: CurrencyModal ->
            p2.price.compareTo(p1.price)
        }
        return list
    }

    fun getCurrencies(): Flow<List<CurrencyModal>> = callbackFlow {
        var currencyList = ArrayList<CurrencyModal>()
        val url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest"
        val queue = Volley.newRequestQueue(context)
        val jsonObjectRequest: JsonObjectRequest = object : JsonObjectRequest(
            Method.GET, url, null,
            Response.Listener { response: JSONObject ->
                // extracting data from json.
                val dataArray = response.getJSONArray("data")
                for (i in 0 until dataArray.length()) {
                    val dataObj = dataArray.getJSONObject(i)
                    val symbol = dataObj.getString("symbol")
                    val name = dataObj.getString("name")
                    val quote = dataObj.getJSONObject("quote")
                    val usd = quote.getJSONObject("USD")
                    val price = usd.getDouble("price")
                    // adding all data to our array list.
                    currencyList.add(CurrencyModal(name, symbol, price))
                }
                //run through filter before displaying
                currencyList = filter(currencyList)
                trySend(currencyList)
            },
            Response.ErrorListener { error: VolleyError? ->
                Timber.e(error)
            }) {
            override fun getHeaders(): Map<String, String> = hashMapOf("X-CMC_PRO_API_KEY" to "af71755f-a268-4319-862a-b928193c1805")
        }
        queue.add(jsonObjectRequest)

        awaitClose {
            Timber.d("Flow closed")
        }
    }
}