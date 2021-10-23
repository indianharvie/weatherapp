package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    TextView email,password;
    Button sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.emailid);
        password=findViewById(R.id.password);
      /*  email.setText("weather@525");
        password.setText("12345");*/
        sign=findViewById(R.id.signin);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = email.getText().toString();
                String pass =  password.getText().toString();
                if(user.equals("weather@525")  &&   pass.equals("12345")) {
                    Intent obj = new Intent(login.this, MainActivity.class);
                    Log.e("check", email.getText() + " "+ password.getText() );
                    startActivity(obj);
                    Toast.makeText(login.this, "Sucessfull", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {

                    Log.e("check", email.getText() + " "+ password.getText() );
                    Toast.makeText(login.this," invalid password",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}