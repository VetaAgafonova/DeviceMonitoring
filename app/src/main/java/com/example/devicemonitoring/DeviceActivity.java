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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class DeviceActivity extends AppCompatActivity{
    TextView indicator;
    Button btnExit;
    ToggleButton tb0, tb1, tb2, tb3, tb4, tb5, tb6, tb7, tb8, tb9, tb10, tb11, tb12, tb13, tb14, tb15;
    ToggleButton stb0, stb1, stb2, stb3, stb4, stb5, stb6, stb7, stb8, stb9, stb10, stb11, stb12, stb13, stb14, stb15;
    private Connection  mConnect;
    String cbHost,cbPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        indicator = findViewById(R.id.indicator);
        btnExit = findViewById(R.id.btnExitConnection);

        tb0 = findViewById(R.id.toggleButton0);
        tb1 = findViewById(R.id.toggleButton1);
        tb2 = findViewById(R.id.toggleButton2);
        tb3 = findViewById(R.id.toggleButton3);
        tb4 = findViewById(R.id.toggleButton4);
        tb5 = findViewById(R.id.toggleButton5);
        tb6 = findViewById(R.id.toggleButton6);
        tb7 = findViewById(R.id.toggleButton7);
        tb8 = findViewById(R.id.toggleButton8);
        tb9 = findViewById(R.id.toggleButton9);
        tb10 = findViewById(R.id.toggleButton10);
        tb11 = findViewById(R.id.toggleButton11);
        tb12 = findViewById(R.id.toggleButton12);
        tb13 = findViewById(R.id.toggleButton13);
        tb14 = findViewById(R.id.toggleButton14);
        tb15 = findViewById(R.id.toggleButton15);
        ///Изменяется через json
        stb0 = findViewById(R.id.tb0_s);
        stb1 = findViewById(R.id.tb1_s);
        stb2 = findViewById(R.id.tb2_s);
        stb3 = findViewById(R.id.tb3_s);
        stb4 = findViewById(R.id.tb4_s);
        stb5 = findViewById(R.id.tb5_s);
        stb6 = findViewById(R.id.tb6_s);
        stb7 = findViewById(R.id.tb7_s);
        stb8 = findViewById(R.id.tb8_s);
        stb9 = findViewById(R.id.tb9_s);
        stb10 = findViewById(R.id.tb10_s);
        stb11 = findViewById(R.id.tb11_s);
        stb12 = findViewById(R.id.tb12_s);
        stb13 = findViewById(R.id.tb13_s);
        stb14 = findViewById(R.id.tb14_s);
        stb15 = findViewById(R.id.tb15_s);
        //Кнопки нельзя нажимать
        stb0.setEnabled(false);
        stb1.setEnabled(false);
        stb2.setEnabled(false);
        stb3.setEnabled(false);
        stb4.setEnabled(false);
        stb5.setEnabled(false);
        stb6.setEnabled(false);
        stb7.setEnabled(false);
        stb8.setEnabled(false);
        stb9.setEnabled(false);
        stb10.setEnabled(false);
        stb11.setEnabled(false);
        stb12.setEnabled(false);
        stb13.setEnabled(false);
        stb14.setEnabled(false);
        stb15.setEnabled(false);

        Resources resources = getResources();
        int backgroundColor = resources.getColor(R.color.green,  null);

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
                    Log.e(Connection.LOG_TAG, e.getMessage());
                    mConnect = null;
                }
            }
        }).start();

        tb0.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("0","1"); else onSendClick("0","0");
            }
        });
        tb1.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("1","1"); else onSendClick("1","0");
            }
        });
        tb2.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("2","1"); else onSendClick("2","0");
            }
        });
        tb3.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("3","1"); else onSendClick("3","0");
            }
        });
        tb4.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("4","1"); else onSendClick("4","0");
            }
        });
        tb5.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("5","1"); else onSendClick("5","0");
            }
        });
        tb6.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("6","1"); else onSendClick("6","0");
            }
        });
        tb7.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("7","1"); else onSendClick("7","0");
            }
        });
        tb8.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("8","1"); else onSendClick("8","0");
            }
        });
        tb9.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("9","1"); else onSendClick("9","0");
            }
        });
        tb10.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("10","1"); else onSendClick("10","0");
            }
        });
        tb11.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("11","1"); else onSendClick("11","0");
            }
        });
        tb12.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("12","1"); else onSendClick("12","0");
            }
        });
        tb13.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("13","1"); else onSendClick("13","0");
            }
        });
        tb14.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("14","1"); else onSendClick("14","0");
            }
        });
        tb15.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("15","1"); else onSendClick("15","0");
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
        Intent intent = new Intent(DeviceActivity.this, MainActivity.class);
        startActivity(intent);
        Log.d(Connection.LOG_TAG, "Соединение закрыто");
    }
    private void onSendClick(String text1, String text2)
    {
        if (mConnect == null) {
            //indicator.setBackgroundColor(backgroundColor2);
            Log.d(Connection.LOG_TAG, "Соединение не установлено");
        }  else {
            Log.d(Connection.LOG_TAG, "Отправка сообщения");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject jo = new JSONObject();
                        jo.put(text1, text2);
                        //System.out.println(jo);
                        /* отправляем на сервер данные */
                        mConnect.sendData(jo.toString().getBytes());
                    } catch (Exception e) {
                        Log.e(Connection.LOG_TAG, e.getMessage());
                    }
                }
            }).start();
        }
    }

}