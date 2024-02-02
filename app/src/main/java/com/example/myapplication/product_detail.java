package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class product_detail extends AppCompatActivity {

    private String title, cat, desc, qte, date, etat, id;
    private TextView titleV, catV, descV, qteV, dateV, etatV;
    private ImageButton delete_btn, update_btn;
    private String URL = "http://192.168.1.102/Android/delete.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        delete_btn = findViewById(R.id.delete);
        update_btn = findViewById(R.id.update);

        titleV = findViewById(R.id.title);
        catV = findViewById(R.id.cat);
        descV = findViewById(R.id.desc);
        qteV = findViewById(R.id.qte);
        dateV = findViewById(R.id.date);
        etatV = findViewById(R.id.etat);

        Bundle extras = getIntent().getExtras();

        id = extras.getString("id");
        title = extras.getString("title");
        cat = extras.getString("cat");
        desc = extras.getString("desc");
        date = extras.getString("date");
        etat = extras.getString("etat");
        qte = extras.getString("qte");

        titleV.setText(title);
        catV.setText(cat);
        descV.setText("Description du produit : " + desc);
        dateV.setText("Publié le : " + date);
        etatV.setText(etat);
        qteV.setText(qte);

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

    }

    private void update() {
        Intent intent = new Intent(getApplicationContext(), update_item.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        intent.putExtra("cat", cat);
        intent.putExtra("desc", desc);
        intent.putExtra("qte", qte);
        intent.putExtra("etat", etat);
        startActivity(intent);
        finish();
    }

    private void delete() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success")) {
                    Toast.makeText(product_detail.this, "Produit Supprimé", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), searchArt.class);
                    startActivity(intent);
                    finish();
                }
                else if (response.equals("Failed")) {
                    Toast.makeText(product_detail.this, "Erreur de connexion", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(product_detail.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("id", id);
                return data;
            }
        };
        queue.add(request);
    }

}