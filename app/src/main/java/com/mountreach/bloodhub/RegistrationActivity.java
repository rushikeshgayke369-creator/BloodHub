package com.mountreach.bloodhub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class RegistrationActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ImageView ivRegisterLogo,ivRegisterTogglePassword;
    boolean isPasswordVisible = false;
    TextView tvRegisterLoginHere;
    EditText etRegisterName,etRegisterMobileNo,etRegisterEmailId,etRegisterUsername,etRegisterPassword;
    Button btnRegisterRegister;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ivRegisterLogo = findViewById(R.id.ivregistration_logo);
        etRegisterName = findViewById(R.id.et_register_name);
        etRegisterMobileNo = findViewById(R.id.et_register_mobileno);
        etRegisterEmailId = findViewById(R.id.et_register_emailid);
        etRegisterUsername = findViewById(R.id.et_register_username);
        etRegisterPassword  = findViewById(R.id.et_register_password);
        btnRegisterRegister = findViewById(R.id.btnregister_register);
        ivRegisterTogglePassword = findViewById(R.id.iv_register_toggle_password);
        tvRegisterLoginHere = findViewById(R.id.tv_Register_LoginHere);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        tvRegisterLoginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        ivRegisterTogglePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isPasswordVisible)
                {
                    etRegisterPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivRegisterTogglePassword.setImageResource(R.drawable.icon_eye_off);
                }
                else {
                    etRegisterPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivRegisterTogglePassword.setImageResource(R.drawable.eye);
                }
                isPasswordVisible = !isPasswordVisible;
                etRegisterPassword.setSelection(etRegisterPassword.getText().length());
            }
        });


        btnRegisterRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etRegisterName.getText().toString().isEmpty())
                {
                    etRegisterName.setError("Please enter your name");
                }
                else if (etRegisterName.getText().toString().length() < 8)
                {
                    etRegisterName.setError("Name must contain at least 8 characters");
                }
                else if (etRegisterMobileNo.getText().toString().isEmpty())
                {
                    etRegisterMobileNo.setError("Please enter your mobile number");
                }
                if(etRegisterMobileNo.getText().toString().length() != 10 )
                {
                    etRegisterMobileNo.setError("Mobile Number Must Contain Atleast 10 digit");
                }
                else if (etRegisterEmailId.getText().toString().isEmpty())
                {
                    etRegisterEmailId.setError("Please enter your email id");
                }
                else if (!etRegisterEmailId.getText().toString().contains("@") || !etRegisterEmailId.getText().toString().contains(".com"))
                {
                    etRegisterEmailId.setError("Please enter valid email id");
                }
                else if (etRegisterUsername.getText().toString().isEmpty())
                {
                    etRegisterUsername.setError("Please enter username");
                } else if (etRegisterUsername.getText().toString().length() < 8)
                {
                    etRegisterUsername.setError("Username must contain atleast 8 characters");
                } else if (etRegisterPassword.getText().toString().isEmpty())
                {
                    etRegisterPassword.setError("Please enter your password");
                } else if (etRegisterPassword.getText().toString().length() < 8)
                {
                    etRegisterPassword.setError("Password length not less than 8");

                }
                else {
                    Toast.makeText(RegistrationActivity.this,"Register Successful!.",Toast.LENGTH_SHORT).show();
                    editor.putString("name",etRegisterName.getText().toString());
                    editor.putString("mobileno",etRegisterMobileNo.getText().toString());
                    editor.putString("emailid",etRegisterEmailId.getText().toString());
                    editor.putString("username",etRegisterUsername.getText().toString());
                    editor.apply();

                    Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        });

    }




    @Override
    public void onBackPressed() {
        Intent i = new Intent(RegistrationActivity.this,LoginActivity.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}