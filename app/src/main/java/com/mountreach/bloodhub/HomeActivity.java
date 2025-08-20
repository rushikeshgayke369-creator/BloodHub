package com.mountreach.bloodhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu; // Import this
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.mountreach.bloodhub.fragment.HomeFragment;
import com.mountreach.bloodhub.fragment.DonateFragment;
import com.mountreach.bloodhub.fragment.ProfileFragment;
import com.mountreach.bloodhub.fragment.RequestForBloodFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;

    private boolean isCalendarVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Force Light Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        // Set the initial toolbar title programmatically
        getSupportActionBar().setTitle("BloodHub");

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        // Set default fragment and update title
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_home);
            getSupportActionBar().setTitle("Home"); // Initial title after starting
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                int itemId = item.getItemId();
                String title = "";

                if (itemId == R.id.nav_home) {
                    selectedFragment = new HomeFragment();
                    title = "Home";
                } else if (itemId == R.id.nav_donate) {
                    selectedFragment = new DonateFragment();
                    title = "Donate";
                } else if (itemId == R.id.nav_request_for_blood) {
                    selectedFragment = new RequestForBloodFragment();
                    title = "Requests";
                } else if (itemId == R.id.nav_profile) {
                    selectedFragment = new ProfileFragment();
                    title = "Profile";
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                    getSupportActionBar().setTitle(title);
                }
                return true;
            };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_donate_history) {
            startActivity(new Intent(this, DonateHistoryActivity.class));

        } else if (id == R.id.nav_hospital_services) {
            startActivity(new Intent(this, HospitalServicesActivity.class));

        } else if (id == R.id.nav_blood_bank_service) {
            startActivity(new Intent(this, BloodBankServiceActivity.class));

        } else if (id == R.id.nav_about_us) {
            startActivity(new Intent(this, AboutUsActivity.class));

        } else if (id == R.id.nav_privacy_policy) {
            startActivity(new Intent(this, PrivacyPolicyActivity.class));

        } else if (id == R.id.nav_rate_us) {
            startActivity(new Intent(this, RateUsActivity.class));

        } else if (id == R.id.nav_notifications) {
            startActivity(new Intent(this, NotificationsActivity.class));

        } else if (id == R.id.nav_logout) {
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish(); // Prevent user from going back to HomeActivity
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // THIS IS THE MISSING METHOD TO INFLATE YOUR MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    // THIS IS THE MISSING METHOD TO HANDLE MENU ITEM CLICKS
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.menuToggleView) {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (currentFragment instanceof DonateFragment) {
                DonateFragment donateFragment = (DonateFragment) currentFragment;

                if (!isCalendarVisible) {
                    donateFragment.showCalendarView();
                    item.setIcon(R.drawable.ic_location);
                    isCalendarVisible = true;
                } else {
                    donateFragment.showMapView();
                    item.setIcon(R.drawable.ic_calendar);
                    isCalendarVisible = false;
                }

            } else {
                Toast.makeText(this, "Toggle only available on Donate tab", Toast.LENGTH_SHORT).show();
            }
            return true;
        } else if (itemId == R.id.menuNotification) {
            Toast.makeText(this, "Notification Clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.menuSettings) {
            Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}