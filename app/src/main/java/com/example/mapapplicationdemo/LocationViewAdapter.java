package com.example.mapapplicationdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LocationViewAdapter extends RecyclerView.Adapter<LocationViewAdapter.ViewHolder> {
    private ArrayList<AddressModel> mData;
    private LayoutInflater mInflater;
    private OnItemClickListener mClickListener;

    LocationViewAdapter(Context context, ArrayList<AddressModel> data, OnItemClickListener mClickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mClickListener = mClickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myTextView.setText(mData.get(position).getAddress());
        holder.deleteAddress.setOnClickListener(v-> mClickListener.deleteAddress(mData.get(position)));
        holder.editAddress.setOnClickListener(v-> mClickListener.editAddress(mData.get(position)));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        AppCompatButton deleteAddress, editAddress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.location_name);
            editAddress = itemView.findViewById(R.id.edit);
            deleteAddress = itemView.findViewById(R.id.delete);
//            itemView.setOnClickListener(this);
        }
    }
}
