package com.mountreach.bloodhub;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ContributeActivity extends AppCompatActivity {

    Button btnContributeNow;
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribute);

        btnContributeNow = findViewById(R.id.btnContributeNow);
        backBtn = findViewById(R.id.backBtn);


        backBtn.setOnClickListener(v -> {
            onBackPressed();
        });


        btnContributeNow.setOnClickListener(v -> {
            Intent intent = new Intent(ContributeActivity.this, ContributionFormActivity.class);
            startActivity(intent);
        });

    }
}

