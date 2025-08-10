package com.mountreach.bloodhub;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mountreach.bloodhub.Fragments.DonateFragment;
import com.mountreach.bloodhub.Fragments.HistoryFragment;
import com.mountreach.bloodhub.Fragments.ProfileFragment;
import com.mountreach.bloodhub.Fragments.RequestFragment;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private boolean isCalendarVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(false); // hide app icon
            getSupportActionBar().setDisplayUseLogoEnabled(false);  // hide logo
            getSupportActionBar().setIcon(null); // make sure no icon is set
        }


        // Bottom Navigation setup
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        loadFragment(new DonateFragment()); // default

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.nav_donate) {
                selectedFragment = new DonateFragment();
            } else if (item.getItemId() == R.id.nav_request) {
                selectedFragment = new RequestFragment();
            } else if (item.getItemId() == R.id.nav_history) {
                selectedFragment = new HistoryFragment();
            } else if (item.getItemId() == R.id.nav_profile) {
                selectedFragment = new ProfileFragment();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
            }
            return true;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuToggleView) {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (currentFragment instanceof DonateFragment) {
                DonateFragment donateFragment = (DonateFragment) currentFragment;

                if (!isCalendarVisible) {
                    donateFragment.showCalendarView();
                    item.setIcon(R.drawable.ic_map);
                    item.setTitle("Map");
                    isCalendarVisible = true;
                } else {
                    donateFragment.showMapView();
                    item.setIcon(R.drawable.ic_calendar);
                    item.setTitle("Calendar");
                    isCalendarVisible = false;
                }
            } else {
                Toast.makeText(this, "Toggle only available on Donate tab", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
