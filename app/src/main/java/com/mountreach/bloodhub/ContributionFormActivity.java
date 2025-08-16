package com.mountreach.bloodhub;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ContributionFormActivity extends AppCompatActivity {

    EditText etName, etMobile, etEmail, etAmount, etFeedback;
    Spinner spinnerPurpose;
    Button btnSubmitContribution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribution_form);


        etName = findViewById(R.id.etName);
        etMobile = findViewById(R.id.etMobile);
        etEmail = findViewById(R.id.etEmail);
        etAmount = findViewById(R.id.etAmount);
        etFeedback = findViewById(R.id.etFeedback);
        btnSubmitContribution = findViewById(R.id.btnSubmitContribution);

        btnSubmitContribution.setOnClickListener(v -> {
            if (validateForm()) {
                // Data collect
                String name = etName.getText().toString().trim();
                String mobile = etMobile.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String purpose = spinnerPurpose.getSelectedItem().toString();
                String amount = etAmount.getText().toString().trim();
                String feedback = etFeedback.getText().toString().trim();


                Toast.makeText(this, "Thank you, " + name + "! Redirecting to payment...", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(ContributionFormActivity.this, PaymentActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("mobile", mobile);
                intent.putExtra("email", email);
                intent.putExtra("purpose", purpose);
                intent.putExtra("amount", amount);
                intent.putExtra("feedback", feedback);
                startActivity(intent);
            }
        });
    }

    private boolean validateForm() {
        if (TextUtils.isEmpty(etName.getText().toString().trim())) {
            etName.setError("Please enter your name");
            return false;
        }
        if (TextUtils.isEmpty(etMobile.getText().toString().trim())) {
            etMobile.setError("Please enter your mobile number");
            return false;
        }
        if (TextUtils.isEmpty(etEmail.getText().toString().trim())) {
            etEmail.setError("Please enter your email");
            return false;
        }
        if (TextUtils.isEmpty(etAmount.getText().toString().trim())) {
            etAmount.setError("Please enter amount");
            return false;
        }
        return true;
    }
}

