package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class addArt extends AppCompatActivity {

    EditText title, desc, qtee, cate;
    String tit, des, qte, cat;
    Button add;
    String URL = "http://192.168.1.102/Android/add_article.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_art);

        title = findViewById(R.id.title);
        desc = findViewById(R.id.des);
        qtee = findViewById(R.id.qte);
        cate = findViewById(R.id.cat);
        add = findViewById(R.id.ajouter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajoutArt();
            }
        });
    }
    public void ajoutArt(){
        tit = title.getText().toString().trim();
        des = desc.getText().toString().trim();
        qte = qtee.getText().toString().trim();
        cat = cate.getText().toString().trim();

        if (!tit.equals("") && !qte.equals("") && !cat.equals("") && !des.equals("")){
            StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Toast.makeText(addArt.this, "Produit Ajouté !", Toast.LENGTH_LONG).show();
                    } else if (response.equals("failure")) {
                        Toast.makeText(addArt.this, "Une erreur s'est generée !", Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(addArt.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("title", tit);
                    data.put("cat", cat);
                    data.put("qte", qte);
                    data.put("des", des);
                    return data;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(addArt.this);
            queue.add(request);
        }else
            Toast.makeText(this, "Veuillez Remplir tout les détails", Toast.LENGTH_SHORT).show();
    }
}