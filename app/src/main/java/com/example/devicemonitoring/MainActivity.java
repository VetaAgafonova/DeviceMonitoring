package com.example.devicemonitoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;




public class MainActivity extends AppCompatActivity {
    Button buttonExit, buttonConnect;
    EditText etName, etHost, etPort, etPassword;
    CheckBox cbRemember;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonExit = findViewById(R.id.btnExit);
        buttonConnect = findViewById(R.id.btnConnect);
        etName = findViewById(R.id.editTextName);
        etHost = findViewById(R.id.editTextHost);
        etPort = findViewById(R.id.editTextTextPort);
        etPassword = findViewById(R.id.editTextPassword);
        cbRemember = findViewById(R.id.checkBox);

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String cbName = preferences.getString("name","");
        String cbHost = preferences.getString("host","");
        String cbPort = preferences.getString("port","");
        String cbPassword = preferences.getString("password","");

        etName.setText(cbName);
        etHost.setText(cbHost);
        etPort.setText(cbPort);
        etPassword.setText(cbPassword);

        cbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("name",etName.getText().toString()).apply();
                    editor.putString("host",etHost.getText().toString()).apply();
                    editor.putString("port",etPort.getText().toString()).apply();
                    editor.putString("password",etPassword.getText().toString()).apply();
                }
            }
        });

        buttonConnect.setOnClickListener(new View.OnClickListener() {
            //Вход не сделан
            public void onClick(View arg0) {

                Intent intent = new Intent(MainActivity.this, DeviceActivity.class);
                startActivity(intent);
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}

