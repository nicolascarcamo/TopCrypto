package com.example.topcrypto;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;



import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

//View

public class MainActivity extends AppCompatActivity {

    // creating variable for recycler view,
    // adapter, array list, progress bar
    private RecyclerView currencyRV;
    private EditText searchEdt;
    private ArrayList<CurrencyModal> currencyModalArrayList;
    private CurrencyRVAdapter currencyRVAdapter;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchEdt = findViewById(R.id.idEdtCurrency);

        // initializing all our variables and array list.
        loadingPB = findViewById(R.id.idPBLoading);
        currencyRV = findViewById(R.id.idRVcurrency);
        currencyModalArrayList = new ArrayList<>();

        // initializing our adapter class.
        currencyRVAdapter = new CurrencyRVAdapter(currencyModalArrayList, this);

        // setting layout manager to recycler view.
        currencyRV.setLayoutManager(new LinearLayoutManager(this));

        // setting adapter to recycler view.
        currencyRV.setAdapter(currencyRVAdapter);

        // calling get data method to get data from API.
        getData();

        // on below line we are adding text watcher for our
        // edit text to check the data entered in edittext.
        searchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // on below line calling a
                // method to filter our array list
            }
        });
    }

    private void filter(ArrayList<CurrencyModal> list) {
        // on below line we are creating a new array list
        // for storing our filtered data.
        // running a for loop to search the data from our array list

        Collections.sort(list, (p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));

        ArrayList<CurrencyModal> filteredList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //filteredList.set(i, list.get(i));
            filteredList.add(list.get(i));
        }

        currencyRVAdapter.filterList(filteredList);
    }

    private void getData() {
        // creating a variable for storing our string.
        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        // creating a variable for request queue.
        RequestQueue queue = Volley.newRequestQueue(this);
        // making a json object request to fetch data from API.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            // inside on response method extracting data
            // from response and passing it to array list
            // on below line we are making our progress
            // bar visibility to gone.
            loadingPB.setVisibility(View.GONE);
            try {
                // extracting data from json.
                JSONArray dataArray = response.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject dataObj = dataArray.getJSONObject(i);
                    String symbol = dataObj.getString("symbol");
                    String name = dataObj.getString("name");
                    JSONObject quote = dataObj.getJSONObject("quote");
                    JSONObject USD = quote.getJSONObject("USD");
                    double price = USD.getDouble("price");
                    // adding all data to our array list.
                    currencyModalArrayList.add(new CurrencyModal(name, symbol, price));
                }
                //Collections.sort(currencyModalArrayList);
                // notifying adapter on data change.
                filter(currencyModalArrayList);
                currencyRVAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                // handling json exception.
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Something went amiss. Please try again later", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            // displaying error response when received any error.
            Toast.makeText(MainActivity.this, "Something went amiss. Please try again later", Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public Map<String, String> getHeaders() {
                // in this method passing headers as
                // key along with value as API keys.
                HashMap<String, String> headers = new HashMap<>();
                headers.put("X-CMC_PRO_API_KEY", "af71755f-a268-4319-862a-b928193c1805");
                // at last returning headers
                return headers;
            }
        };
        // calling a method to add our
        // json object request to our queue.
        queue.add(jsonObjectRequest);
    }
}
