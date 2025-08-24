package com.mountreach.bloodhub;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class LoginActivity extends AppCompatActivity {
    ImageView ivLoginLogo;
    EditText etLoginEmailOrPhone,etLoginPassword;
    TextView tvForgotPassword,tvLoginSignIn;
    Button btnLogin;
    CheckBox cbLoginShowPassword;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    public boolean doubletap = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        setTitle("Login Here");


        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        if(preferences.getBoolean("islogin",false))
        {
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }


        ivLoginLogo = findViewById(R.id.ivloginlogo);
        etLoginEmailOrPhone = findViewById(R.id.etlogin_email_or_phone);
        etLoginPassword = findViewById(R.id.etlogin_password);
        tvForgotPassword = findViewById(R.id.tv_login_forgotpassword);
        tvLoginSignIn = findViewById(R.id.tvlogin_sign_in);
        btnLogin = findViewById(R.id.btn_login);
        cbLoginShowPassword = findViewById(R.id.cblogin_show_password);


        tvLoginSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });


        cbLoginShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    etLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    etLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }

            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etLoginEmailOrPhone.getText().toString().isEmpty())
                {
                    etLoginEmailOrPhone.setError("Please Enter Email");
                }
                else if(etLoginEmailOrPhone.getText().toString().length() < 8)
                {
                    etLoginEmailOrPhone.setError("Email should be At Least 8 Characters long");
                }
                else if (!etLoginEmailOrPhone.getText().toString().contains("@") || !etLoginEmailOrPhone.getText().toString().contains(".com"))
                {
                    etLoginEmailOrPhone.setError("Please enter valid email id");
                }
                else if (!etLoginEmailOrPhone.getText().toString().matches(".*[a-z].*"))
                {
                    etLoginEmailOrPhone.setError("Email Must Contain At least 1 Lowercase Letter");
                }
                else if (!etLoginEmailOrPhone.getText().toString().matches(".*\\d.*"))
                {
                    etLoginEmailOrPhone.setError("Email Must Contain At least 1 number");
                }
                else if (!etLoginEmailOrPhone.getText().toString().matches(".*[!@#$%^&*-+=].*"))
                {
                    etLoginEmailOrPhone.setError("Email Must Contain At least 1 Special Symbol");
                }

                else if(etLoginPassword.getText().toString().isEmpty())
                {
                    etLoginPassword.setError("Please Enter Password");
                }
                else if(etLoginPassword.getText().toString().length() < 8)
                {
                    etLoginPassword.setError("Password Length Not Less Than 8");
                }
                else if (!etLoginPassword.getText().toString().matches(".*[!@#$%^&*-=+].*"))
                {
                    etLoginPassword.setError("Password Must Contain At least 1 Special Symbol");
                }
                else if (!etLoginPassword.getText().toString().matches(".*\\d.*"))
                {
                    etLoginPassword.setError("Password Must Contain At least 1 Number");
                }

                else if (!etLoginPassword.getText().toString().matches(".*[a-z].*"))
                {
                    etLoginPassword.setError("Password Must Contain At least 1 Lowercase Letter");
                }
                else{
                    Toast.makeText(LoginActivity.this,"Login SuccessFul!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    editor.putBoolean("islogin",true).commit();
                    startActivity(intent);

                    editor.putString("Email",etLoginEmailOrPhone.getText().toString()).commit();
                    finish();

                }
            }

        });

    }

    @Override
    public void onBackPressed() {
        if(doubletap)
        {
            finishAffinity();
            super.onBackPressed();
        }
        else {
            this.doubletap = true;
            Toast.makeText(this,"Press again to exit",Toast.LENGTH_SHORT).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubletap = false;
                }
            },2000);
        }
    }
}