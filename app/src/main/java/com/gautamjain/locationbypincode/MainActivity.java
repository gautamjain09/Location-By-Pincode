package com.gautamjain.locationbypincode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText EnterPincode;

    private RecyclerView recyclerView;
    private ArrayList<CurrentModal> arraylist;
    private CurrentAdapter currentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EnterPincode = findViewById(R.id.EnterPincode);

        arraylist = new ArrayList<>();
        currentAdapter = new CurrentAdapter(arraylist, this);

        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(currentAdapter);

        // API CALL
        getCurrentData();

        //Search
        EnterPincode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                filterCurrentData(s.toString());
            }
        });
    }

    private void filterCurrentData(String currency)
    {
        ArrayList<CurrentModal> filteredList = new ArrayList<>();
        for(CurrentModal it : arraylist)
        {
            if(it.getPlaceName().toLowerCase().contains(currency.toLowerCase()))
            {
                filteredList.add(it);
            }
            else
            {
                currentAdapter.filterList(filteredList);
            }
        }

//        if(filteredList.isEmpty())
//        {
//            Toast.makeText(this, "No such Currency found", Toast.LENGTH_SHORT).show();
//        }

    }


    private void getCurrentData() {
        String url = "http://api.zippopotam.us/in/" + EnterPincode.getText();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray dataArray = response.getJSONArray("places");

                            for (int i = 0; i < dataArray.length(); i++) {

                                JSONObject dataObject = dataArray.getJSONObject(i);

                                String mstateName = dataObject.getString("state");
                                String mstateAbbrevation = dataObject.getString("state abbreviation");
                                double mlatitude = dataObject.getDouble("latitude");
                                double mlongitude = dataObject.getDouble("longitude");
                                String mplaceName = dataObject.getString("place name");

                                arraylist.add(new CurrentModal(mstateName, mstateAbbrevation, mlatitude, mlongitude, mplaceName));

                            }

                            currentAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Fail to get Data!", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage() , Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> mp = new HashMap<>();
                // Key, Value
                mp.put("X-PINCODE-API-KEY", "postmanapivalue");
                return mp;
            }
        };

        requestQueue.add(jsonObjectRequest);

    }

}