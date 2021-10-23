package com.example.weatherapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextInputEditText searchcity;
    TextView temp,weatherstate,cityname,humidity;
    TextView textView;
    Button srchbtn;
    //for getting auto current location



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().hide();
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.gle));
         //   getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        }
        searchcity=findViewById(R.id.cityname);
        temp=findViewById(R.id.temp);
        cityname=findViewById(R.id.autocity);
        weatherstate=findViewById(R.id.wethercondition);
        srchbtn=findViewById(R.id.srchbttton);
        humidity=findViewById(R.id.humidity);


       srchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String str="e78b581a08175cc0116fec93dc6a74e7";
                String city1=searchcity.getText().toString();
                String url="https://api.openweathermap.org/data/2.5/weather?q="+city1+"&appid=e78b581a08175cc0116fec93dc6a74e7";
               // to genrate the reques for data
                RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject object=response.getJSONObject("main");
                            String temprature=object.getString("temp");
                            String humidity2=object.getString("humidity");




                          //  int count=°F = (°C x 1.8) + 32;
                            //int t=Integer.parseInt(temprature);
                           // Toast.makeText(getApplicationContext(),t,Toast.LENGTH_LONG).show();

                           // float c= (float) ((t-32)/1.8);
                            temp.setText(temprature+" °C");
                            humidity.setText("H "+humidity2);

                             object=response.getJSONObject("sys");

                            String cityname2=object.getString("country");
                            cityname.setText(city1+" , "+cityname2);
                            /*JSONObject wea=(new JSONObject()).getJSONObject("weather");
                            String weather=wea.getString("main");
                            String str="Employee Name:"+weather;
                            weatherstate.setText(str);*/

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


        queue.add(request);
            }
        });

    }

}