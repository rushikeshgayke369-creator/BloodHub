package com.mountreach.bloodhub.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mountreach.bloodhub.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DonateFragment extends Fragment implements OnMapReadyCallback {
//a
    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private SearchView searchView;
    private CalendarView calendarView;
    private LinearLayout mapContainer;
    private SupportMapFragment cachedMapFragment;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;

    public DonateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donate, container, false);

        // Initialize views
        mapContainer = view.findViewById(R.id.mapContainer);
        calendarView = view.findViewById(R.id.calendarView);
        searchView = view.findViewById(R.id.location_search);

        calendarView.setVisibility(View.GONE); // Hide calendar by default

        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            String selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
            Toast.makeText(getContext(), "Selected: " + selectedDate, Toast.LENGTH_SHORT).show();
        });

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        // Initialize and cache map fragment
        cachedMapFragment = new SupportMapFragment();
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.mapFrame, cachedMapFragment)
                .commit();
        cachedMapFragment.getMapAsync(this);

        // Setup search
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
                try {
                    List<Address> addresses = geocoder.getFromLocationName(query, 1);
                    if (addresses != null && !addresses.isEmpty()) {
                        double latitude = addresses.get(0).getLatitude();
                        double longitude = addresses.get(0).getLongitude();

                        LatLng location = new LatLng(latitude, longitude);
                        googleMap.clear();
                        googleMap.addMarker(new MarkerOptions().position(location).title(query));
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
                    } else {
                        Toast.makeText(requireContext(), "Location not found", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(requireContext(), "Error finding location", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;
    }

    public void showCalendarView() {
        if (mapContainer != null && calendarView != null) {
            mapContainer.setVisibility(View.GONE);
            calendarView.setAlpha(0f);
            calendarView.setVisibility(View.VISIBLE);
            calendarView.animate().alpha(1f).setDuration(300).start();

            Fragment mapFragment = getChildFragmentManager().findFragmentById(R.id.mapFrame);
            if (mapFragment != null) {
                getChildFragmentManager()
                        .beginTransaction()
                        .remove(mapFragment)
                        .commit();
            }
        }
    }

    public void showMapView() {
        if (mapContainer != null && calendarView != null) {
            calendarView.setVisibility(View.GONE);
            mapContainer.setVisibility(View.VISIBLE);

            if (cachedMapFragment == null) {
                cachedMapFragment = new SupportMapFragment();
            }

            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mapFrame, cachedMapFragment)
                    .commit();

            // Always reinitialize map
            cachedMapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        googleMap = map;
        enableUserLocation();
    }

    private void enableUserLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        googleMap.setMyLocationEnabled(true);

        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15));
                        googleMap.addMarker(new MarkerOptions()
                                .position(userLocation)
                                .title("You are here"));
                    } else {
                        Toast.makeText(requireContext(), "Unable to get location", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableUserLocation();
            } else {
                Toast.makeText(requireContext(), "Location permission is required to show your position", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
