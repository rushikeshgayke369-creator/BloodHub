package com.mountreach.bloodhub;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mountreach.bloodhub.R;

public class PrivacyPolicyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        // Load the HTML-formatted text from string resources
        TextView tvIntro = findViewById(R.id.tv_intro);
        TextView tvDataCollection = findViewById(R.id.tv_data_collection);
        TextView tvDataUse = findViewById(R.id.tv_data_use);
        TextView tvDataProtection = findViewById(R.id.tv_data_protection);
        TextView tvDataSharing = findViewById(R.id.tv_data_sharing);
        TextView tvUserRights = findViewById(R.id.tv_user_rights);
        TextView tvSecurityMeasures = findViewById(R.id.tv_security_measures);
        TextView tvConsent = findViewById(R.id.tv_consent);
        TextView tvChanges = findViewById(R.id.tv_changes);
        TextView tvContact = findViewById(R.id.tv_contact);
        TextView tvFinalNote = findViewById(R.id.tv_final_note);

        // For Android versions Nougat (API 24) and above
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvIntro.setText(Html.fromHtml(getString(R.string.section_intro), Html.FROM_HTML_MODE_LEGACY));
            tvDataCollection.setText(Html.fromHtml(getString(R.string.section_data_collection), Html.FROM_HTML_MODE_LEGACY));
            tvDataUse.setText(Html.fromHtml(getString(R.string.section_data_use), Html.FROM_HTML_MODE_LEGACY));
            tvDataProtection.setText(Html.fromHtml(getString(R.string.section_data_protection), Html.FROM_HTML_MODE_LEGACY));
            tvDataSharing.setText(Html.fromHtml(getString(R.string.section_data_sharing), Html.FROM_HTML_MODE_LEGACY));
            tvUserRights.setText(Html.fromHtml(getString(R.string.section_user_rights), Html.FROM_HTML_MODE_LEGACY));
            tvSecurityMeasures.setText(Html.fromHtml(getString(R.string.section_security_measures), Html.FROM_HTML_MODE_LEGACY));
            tvConsent.setText(Html.fromHtml(getString(R.string.section_consent), Html.FROM_HTML_MODE_LEGACY));
            tvChanges.setText(Html.fromHtml(getString(R.string.section_changes_policy), Html.FROM_HTML_MODE_LEGACY));
            tvContact.setText(Html.fromHtml(getString(R.string.section_contact), Html.FROM_HTML_MODE_LEGACY));
            tvFinalNote.setText(Html.fromHtml(getString(R.string.section_final_note), Html.FROM_HTML_MODE_LEGACY));
        } else {
            // For older Android versions
            tvIntro.setText(Html.fromHtml(getString(R.string.section_intro)));
            tvDataCollection.setText(Html.fromHtml(getString(R.string.section_data_collection)));
            tvDataUse.setText(Html.fromHtml(getString(R.string.section_data_use)));
            tvDataProtection.setText(Html.fromHtml(getString(R.string.section_data_protection)));
            tvDataSharing.setText(Html.fromHtml(getString(R.string.section_data_sharing)));
            tvUserRights.setText(Html.fromHtml(getString(R.string.section_user_rights)));
            tvSecurityMeasures.setText(Html.fromHtml(getString(R.string.section_security_measures)));
            tvConsent.setText(Html.fromHtml(getString(R.string.section_consent)));
            tvChanges.setText(Html.fromHtml(getString(R.string.section_changes_policy)));
            tvContact.setText(Html.fromHtml(getString(R.string.section_contact)));
            tvFinalNote.setText(Html.fromHtml(getString(R.string.section_final_note)));
        }

        // Set up the bottom navigation listener (No change from previous)
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                return true;
            } else if (itemId == R.id.nav_donate) {

                return true;
            } else if (itemId == R.id.nav_request_for_blood) {
                return true;
            }else if (itemId == R.id.nav_profile) {
                return true;
            }else if (itemId == R.id.nav_home) {
                return true;
            }
            return false;
        });
    }
}