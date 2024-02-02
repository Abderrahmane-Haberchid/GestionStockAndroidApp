package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class dashboard extends AppCompatActivity {

    protected ImageButton addbtn, searchbtn, compte, statis;
    String mail;
    TextView mailTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        searchbtn = findViewById(R.id.search_btn);
        addbtn = findViewById(R.id.add_btn);
        compte = findViewById(R.id.account_btn);
        statis = findViewById(R.id.statis_btn);


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAdd();
            }
        });
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSearch();
            }
        });
        compte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCompte();
            }
        });
        statis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStatis();
            }
        });
    }

    private void showStatis() {
        Intent intent = new Intent(getApplicationContext(), statis.class);
        startActivity(intent);
    }

    private void showCompte() {
        Intent intent = new Intent(getApplicationContext(), compte.class);
        intent.putExtra("mail", mail);
        startActivity(intent);

    }

    public void showAdd(){
        Intent intent = new Intent(getApplicationContext(), addArt.class);
        intent.putExtra("mail", mail);
        startActivity(intent);
    }
    public void showSearch(){
        Intent intent = new Intent(getApplicationContext(), searchArt.class);
        intent.putExtra("mail", mail);
        startActivity(intent);
    }
}