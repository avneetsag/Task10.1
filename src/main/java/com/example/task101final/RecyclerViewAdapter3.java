package com.example.task101final;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter3 extends RecyclerView.Adapter<RecyclerViewAdapter3.MyViewHolder> {
    Context context;
    Activity activity;
    List<Model3> myCart;

    public RecyclerViewAdapter3(Context context, Activity activity, List<Model3> myCart) {
        this.context = context;
        this.activity = activity;
        this.myCart = myCart;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter3.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout_cart, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter3.MyViewHolder holder, int position) {

        holder.id.setText(myCart.get(position).getId());
        holder.title.setText(myCart.get(position).getTitle());


    }

    @Override
    public int getItemCount() {
        return myCart.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, title;
        RelativeLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_cart);
            id = itemView.findViewById(R.id.counts);
            layout = itemView.findViewById(R.id.note_layout2);
        }
    }
}