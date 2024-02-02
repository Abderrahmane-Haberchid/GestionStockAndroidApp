package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.userHolder>{

    LayoutInflater inflater;
    ArrayList<User> listuser;
    String id;

    public UserAdapter(Context context, ArrayList<User> listuser){
        this.inflater = LayoutInflater.from(context);
        this.listuser = listuser;
    }
    public class userHolder extends RecyclerView.ViewHolder {
        TextView iduser, name, statut;
        ImageButton dele;
        public userHolder(@NonNull View itemView) {
            super(itemView);

            iduser = itemView.findViewById(R.id.iduser);
            name = itemView.findViewById(R.id.name);
            statut = itemView.findViewById(R.id.statut);
            dele = itemView.findViewById(R.id.dele);
        }
    }
    @NonNull
    @Override
    public userHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.activity_user_detail, parent, false);
        return new userHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull userHolder holder, int position) {
        holder.iduser.setText(listuser.get(position).getIduser());
        holder.name.setText(listuser.get(position).getName());
        holder.statut.setText(listuser.get(position).getStatut());

        holder.dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String URL1 = "http://192.168.1.102/android/deleteuser.php?id="+holder.iduser.getText().toString();

                StringRequest request = new StringRequest(Request.Method.GET, URL1, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")) {
                            Toast.makeText(v.getContext(), "User supprimé avec succes !", Toast.LENGTH_LONG).show();
                        }
                        if (response.equals("failure")) {
                            Toast.makeText(v.getContext(), "Une erreur s'est produite !", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(v.getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
                RequestQueue queue = Volley.newRequestQueue(v.getContext());
                queue.add(request);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listuser.size();
    }




}
