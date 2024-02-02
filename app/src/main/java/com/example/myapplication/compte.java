package com.example.myapplication;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class compte extends AppCompatActivity {

    private TextView username, email, statut;
    private String mail;
    private Button sup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        statut = findViewById(R.id.statut);
        sup = findViewById(R.id.adminctrl);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("usermail", Context.MODE_PRIVATE);
        mail = sp.getString("email", "");

        showData();

        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), allUser.class);
                startActivity(intent);
            }
        });

    }

    public void showData(){
        String URL = "http://192.168.1.102/Android/showUser.php?mail="+mail;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override

            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        username.setText(jsonObject.getString("name"));
                        email.setText(jsonObject.getString("email"));
                        statut.setText(jsonObject.getString("statut"));
                        if (jsonObject.getString("statut").equals("admin")) {
                            sup.setVisibility(View.VISIBLE);
                            Toast.makeText(compte.this, "Mode Admin", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(compte.this,"error json", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                 Toast.makeText(compte.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request);
    }
}