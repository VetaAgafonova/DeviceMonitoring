package com.example.devicemonitoring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class DeviceActivity extends AppCompatActivity{
    EditText edTxt;
    TextView indicator;
    Button btnExit, btnSend;
    private Connection  mConnect;
    String cbHost,cbPort;
    Resources resources = getResources();
    int backgroundColor = resources.getColor(R.color.green,  null);
    int backgroundColor2 = resources.getColor(R.color.red,  null);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        indicator = findViewById(R.id.indicator);
        btnExit = findViewById(R.id.btnExitConnection);
        btnSend = findViewById(R.id.button);
        edTxt = findViewById(R.id.editText11);




        Bundle arguments = getIntent().getExtras();
        if(arguments!=null){
            cbHost = arguments.get("cbHost").toString();
            cbPort = arguments.get("cbPort").toString();
        }
        mConnect = new Connection(cbHost ,Integer.parseInt(cbPort));
        // Открытие сокета в отдельном потоке
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mConnect.openConnection();
                    indicator.setBackgroundColor(backgroundColor);
                    Log.d(Connection.LOG_TAG, "Соединение установлено");
                    Log.d(Connection.LOG_TAG, "(mConnect != null) = "
                            + (mConnect != null));
                } catch (Exception e) {
                    indicator.setBackgroundColor(backgroundColor2);
                    Log.e(Connection.LOG_TAG, e.getMessage());
                    mConnect = null;
                }
            }
        }).start();
        btnSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                onSendClick();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                onCloseClick();
            }
        });



    }

    private void onCloseClick()
    {
        // Закрытие соединения
        mConnect.closeConnection();
        indicator.setBackgroundColor(backgroundColor2);
        Intent intent = new Intent(DeviceActivity.this, MainActivity.class);
        startActivity(intent);
        Log.d(Connection.LOG_TAG, "Соединение закрыто");
    }
    private void onSendClick()
    {
        if (mConnect == null) {
            indicator.setBackgroundColor(backgroundColor2);
            Log.d(Connection.LOG_TAG, "Соединение не установлено");
        }  else {
            Log.d(Connection.LOG_TAG, "Отправка сообщения");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String text = edTxt.getText().toString();
                        if (text.trim().length() == 0)
                            text = "Test message";
                        /* отправляем на сервер данные */
                        mConnect.sendData(text.getBytes());
                    } catch (Exception e) {
                        Log.e(Connection.LOG_TAG, e.getMessage());
                    }
                }
            }).start();
        }
    }
}