package com.example.cardatabase;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {
    ListView IvWeather;
    CarAdapter aaCar;
    ArrayList<Car> alCar;
    ArrayList <String> alCC;
    //Allow app to receive and read JSON data
    AsyncHttpClient client;
    Button filter;
    Spinner spinner;
    //URL to get JSON
    private static String url = "https://data.gov.sg/api/action/datastore_search?resource_id=f6bdfe4c-bde0-4313-a10b-82bd0ac634a9&limit=10";

    //JSON Node Names
    private static final String TAG_CC = "cc_ratings";
    private static final String TAG_NUMBER = "number";
    private static final String TAG_YEAR = "year";

    //Data JSONArray
    JSONArray users = null;

    //ArrayList to keep the data



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IvWeather = findViewById(R.id.ListView);
        client = new AsyncHttpClient();
        filter = findViewById(R.id.BFilter);
        spinner = findViewById(R.id.spinnerCC);
        alCC = new ArrayList<String>();

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choseRating = alCC.get(spinner.getSelectedItemPosition());
                if (choseRating.equalsIgnoreCase("no filter")) {

                } else {
                    alCar.clear();

                    aaCar.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, spinner.getSelectedItem().toString() + " is FILTERED", Toast.LENGTH_LONG).show();
                }


            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        alCar = new ArrayList<Car>();
        aaCar = new CarAdapter(MainActivity.this, R.layout.row, alCar);
        IvWeather.setAdapter(aaCar);
        super.onResume();


        client.get("https://data.gov.sg/api/action/datastore_search?resource_id=f6bdfe4c-bde0-4313-a10b-82bd0ac634a9&limit=10", new JsonHttpResponseHandler() {

            String year;
            String ccrating;
            String number;

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONObject resultItems = response.getJSONObject("result");
                    JSONArray jsonArrForecasts = resultItems.getJSONArray("records");

                    for (int i = 0; i < jsonArrForecasts.length(); i++) {
                        JSONObject jsonObjForecast = jsonArrForecasts.getJSONObject(i);
                        year = jsonObjForecast.getString("year");
                        ccrating = jsonObjForecast.getString("cc_rating");
                        number = jsonObjForecast.getString("number");
                        Car car = new Car(year, ccrating, number);
                        alCar.add(car);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }

                //POINT X – Code to display List View
                aaCar.notifyDataSetChanged();


            }//end onSuccess
        });
    }//end onResume
}
