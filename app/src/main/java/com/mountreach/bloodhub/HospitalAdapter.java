package com.mountreach.bloodhub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder> {

    ArrayList<String> hospitals;

    public HospitalAdapter(ArrayList<String> hospitals) {
        this.hospitals = hospitals;
    }

    @NonNull
    @Override
    public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hospital, parent, false);
        return new HospitalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalViewHolder holder, int position) {
        String item = hospitals.get(position);
        String[] parts = item.split(" - "); // split name and distance
        holder.tvName.setText(parts[0]);
        holder.tvDistance.setText(parts[1]);
    }

    @Override
    public int getItemCount() {
        return hospitals.size();
    }

    static class HospitalViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHospital, imgDirection;
        TextView tvName, tvDistance;

        public HospitalViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHospital = itemView.findViewById(R.id.imgHospital);
            imgDirection = itemView.findViewById(R.id.imgDirection);
            tvName = itemView.findViewById(R.id.tvHospitalName);
            tvDistance = itemView.findViewById(R.id.tvHospitalDistance);
        }
    }
}
