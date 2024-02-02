package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.itemHolder> implements Filterable {

    LayoutInflater inflater;
    ArrayList<Product> listProduct;
    ArrayList<Product> listProductAll;

    public RecyclerAdapter(Context context, ArrayList<Product> listProduct){
        this.inflater = LayoutInflater.from(context);
        this.listProduct = listProduct;
        this.listProductAll = new ArrayList<>(listProduct);
    }

    @Override
    public void onBindViewHolder(@NonNull itemHolder holder, int position) {

        holder.idprod.setText(listProduct.get(position).getId());
        holder.title.setText(listProduct.get(position).getTitle());
        holder.date.setText(listProduct.get(position).getDate());
        holder.cat.setText(listProduct.get(position).getCat());
        holder.etat.setText(listProduct.get(position).getEtat());
        holder.desc.setText(listProduct.get(position).getDesc());
        holder.qte.setText(listProduct.get(position).getQte());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView idpass = v.findViewById(R.id.idprod);
                TextView title = v.findViewById(R.id.tit);
                TextView cat = v.findViewById(R.id.cat);
                TextView desc = v.findViewById(R.id.desc);
                TextView date = v.findViewById(R.id.date);
                TextView etat = v.findViewById(R.id.etat);
                TextView qte = v.findViewById(R.id.qte);

                Intent intent = new Intent(v.getContext(), product_detail.class);

                intent.putExtra("id", idpass.getText().toString());
                intent.putExtra("title", title.getText().toString());
                intent.putExtra("cat", cat.getText().toString());
                intent.putExtra("desc", desc.getText().toString());
                intent.putExtra("date", date.getText().toString());
                intent.putExtra("etat", etat.getText().toString());
                intent.putExtra("qte", qte.getText().toString());

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class itemHolder extends RecyclerView.ViewHolder {

        private TextView title, desc, qte, date, cat, etat, idprod;

        public itemHolder(@NonNull View itemView) {
            super(itemView);

            idprod = itemView.findViewById(R.id.idprod);
            title = itemView.findViewById(R.id.tit);
            date = itemView.findViewById(R.id.date);
            cat = itemView.findViewById(R.id.cat);
            etat = itemView.findViewById(R.id.etat);
            desc = itemView.findViewById(R.id.desc);
            qte = itemView.findViewById(R.id.qte);
        }

    }

    @NonNull
    @Override
    public itemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.product_item, parent, false);
        return new itemHolder(v);
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Product> listFiltree = new ArrayList<>();

            if (constraint.toString() == "" || constraint.length() == 0){
                listFiltree.addAll(listProduct);
            }
            else{
                for (Product product: listProduct){
                    String src = constraint.toString().toLowerCase().trim();
                    if (product.getDesc().toLowerCase().contains(src) || product.getTitle().toLowerCase().contains(src)){
                        listFiltree.add(product);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = listFiltree;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listProduct.clear();
            listProduct.addAll((List) results.values);

            notifyDataSetChanged();
        }
    };


}
