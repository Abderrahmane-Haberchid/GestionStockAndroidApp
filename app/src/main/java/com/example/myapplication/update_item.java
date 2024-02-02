package com.example.myapplication;

import static android.os.Build.VERSION_CODES.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class update_item extends AppCompatActivity {

    private EditText title, cat, desc, qte, etat;
    private String ntitle, ncat, ndesc, nqte, netat, id;
    private Button update_btn;
    private String URL = "http://192.168.1.102/Android/update.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);

        update_btn = findViewById(R.id.update);

        title = findViewById(R.id.title);
        cat = findViewById(R.id.cat);
        desc = findViewById(R.id.desc);
        qte = findViewById(R.id.qte);
        etat = findViewById(R.id.etat);

        Bundle extras = getIntent().getExtras();

        ntitle = extras.getString("title");
        ncat = extras.getString("cat");
        ndesc = extras.getString("desc");
        nqte = extras.getString("qte");
        netat = extras.getString("etat");
        id = extras.getString("id");

        title.setText(ntitle);
        cat.setText(ncat);
        desc.setText(ndesc);
        qte.setText(nqte);
        etat.setText(netat);

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

    }

    private void update() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")) {
                    Toast.makeText(update_item.this, response.toString(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), searchArt.class);
                    startActivity(intent);
                    finish();
                }
                else if (response.equals("fail")) {
                    Toast.makeText(update_item.this, "une erreur s'est generée !", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(update_item.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("id", id);
                data.put("title", title.getText().toString());
                data.put("cat", cat.getText().toString());
                data.put("desc", desc.getText().toString());
                data.put("qte", qte.getText().toString());
                data.put("etat", etat.getText().toString());
                return data;
            }
        };
        queue.add(request);
    }
}