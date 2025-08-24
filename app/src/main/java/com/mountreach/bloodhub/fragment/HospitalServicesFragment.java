package com.mountreach.bloodhub.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mountreach.bloodhub.HospitalAdapter;
import com.mountreach.bloodhub.R;

import java.util.ArrayList;

public class HospitalServicesFragment extends Fragment {

    RecyclerView recyclerView;
    HospitalAdapter adapter;
    ArrayList<String> hospitalList;

    public HospitalServicesFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hospital_services, container, false);

        recyclerView = view.findViewById(R.id.recyclerHospitals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Sample Data (you can later fetch from API/DB)
        hospitalList = new ArrayList<>();
        hospitalList.add("City Hospital - 2 km");
        hospitalList.add("Shree Medical Center - 3 km");
        hospitalList.add("LifeCare Hospital - 4.5 km");
        hospitalList.add("Sanjeevani Hospital - 5 km");

        adapter = new HospitalAdapter(hospitalList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
