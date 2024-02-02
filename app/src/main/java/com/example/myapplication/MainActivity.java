package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    protected Button btninscription, btnvalider;
    protected EditText email, mdp;
    private String mail, mp;
    String URL = "http://192.168.1.102/Android/login.php";
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email_field);
        mdp = findViewById(R.id.mdp_field);
        btnvalider = (Button) findViewById(R.id.btn_id);
        btninscription = (Button) findViewById(R.id.btninsc);

        sp = getSharedPreferences("usermail", Context.MODE_PRIVATE);

        btninscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegister();
            }
        });

        btnvalider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    public void showRegister(){
        Intent intent = new Intent(getApplicationContext(), register.class);
        startActivity(intent);
    }

    public void login(){
        mail = email.getText().toString().trim();
        mp = mdp.getText().toString().trim();

        if (!mail.equals("") && !mp.equals("")) {

                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")) {
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("email", mail);
                            editor.commit();
                            Intent intent = new Intent(getApplicationContext(), dashboard.class);
                            intent.putExtra("mail", mail);
                            startActivity(intent);

                        }else if (response.equals("failure"))
                            Toast.makeText(MainActivity.this, "Erreur de Connexion au DB", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();
                        data.put("mail", mail);
                        data.put("mdp", mp);
                        return data;
                    }
                };

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(request);

        } else Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();


    }

}