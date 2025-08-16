package com.mountreach.bloodhub;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    EditText etCardNumber, etExpiryDate, etCvv;
    Button btnPayNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        etCardNumber = findViewById(R.id.etCardNumber);
        etExpiryDate = findViewById(R.id.etExpiryDate);
        etCvv = findViewById(R.id.etCvv);
        btnPayNow = findViewById(R.id.btnPayNow);

        btnPayNow.setOnClickListener(v -> {
            String cardNumber = etCardNumber.getText().toString().trim();
            String expiryDate = etExpiryDate.getText().toString().trim();
            String cvv = etCvv.getText().toString().trim();

            if (TextUtils.isEmpty(cardNumber) || cardNumber.length() != 16) {
                etCardNumber.setError("Enter valid 16-digit card number");
                return;
            }
            if (TextUtils.isEmpty(expiryDate) || !expiryDate.matches("(0[1-9]|1[0-2])/[0-9]{2}")) {
                etExpiryDate.setError("Enter valid expiry date (MM/YY)");
                return;
            }
            if (TextUtils.isEmpty(cvv) || cvv.length() != 3) {
                etCvv.setError("Enter valid 3-digit CVV");
                return;
            }

            Toast.makeText(PaymentActivity.this, "Payment Successful!", Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
