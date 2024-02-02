package com.example.myapplication;

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

public class register extends AppCompatActivity {
    protected EditText name, email, mdp, cmdp ;
    protected Button valider;
    protected String ename, mail, emdp, ecmdp;
    String URL = "http://192.168.1.102/Android/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name_txt);
        email = findViewById(R.id.email_txt);
        mdp = findViewById(R.id.mdp_txt);
        cmdp = findViewById(R.id.cmdp_txt);
        valider = findViewById(R.id.valider_btn);

        ename = mail = emdp = ecmdp = "";

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });

    }

    public void addUser() {
        ename = name.getText().toString().trim();
        mail = email.getText().toString().trim();
        emdp = mdp.getText().toString().trim();
        ecmdp = cmdp.getText().toString().trim();

        if (!ename.equals("") && !mail.equals("") && !emdp.equals("") && !ecmdp.equals("")) {
            if (emdp.equals(ecmdp)) {

                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")) {
                            Toast.makeText(register.this, "Compte Créé avec succes", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else if (response.equals("failure"))
                            Toast.makeText(register.this, "Login ou mot de passe incorrect", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(register.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();
                        data.put("name", ename);
                        data.put("mail", mail);
                        data.put("mdp", emdp);
                        return data;
                    }
                };

                RequestQueue queue = Volley.newRequestQueue(register.this);
                queue.add(request);
            } else
                Toast.makeText(this, "Votre mot de passe n'est pas identiques", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();


    }
}