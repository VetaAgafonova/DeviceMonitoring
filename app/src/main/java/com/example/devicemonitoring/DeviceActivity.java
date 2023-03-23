package com.example.devicemonitoring;

import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class DeviceActivity extends AppCompatActivity implements MyCustomInterface{
    TextView indicator, name, ready, ai0, ai1, ai2, ai3, ai4, ai5;
    Button btnExit, btn1, btn2, btn3, btn4, btn5;
    ToggleButton tb0, tb1, tb2, tb3, tb4, tb5, tb6, tb7, tb8, tb9, tb10, tb11, tb12, tb13, tb14, tb15;
    ToggleButton stb0, stb1, stb2, stb3, stb4, stb5, stb6, stb7, stb8, stb9, stb10, stb11, stb12, stb13, stb14, stb15;
    private Connection  mConnect;
    String cbHost,cbPort;
    DeviceClass [] myDevices = new DeviceClass[5];
    JSONObject js;
    int devCurr = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);

        indicator = findViewById(R.id.indicator);
        name = findViewById(R.id.textViewDevName);
        ready = findViewById(R.id.textViewDevReady);
        ai0 = findViewById(R.id.textViewPar1);
        ai1 = findViewById(R.id.textViewPar2);
        ai2 = findViewById(R.id.textViewPar3);
        ai3 = findViewById(R.id.textViewPar4);
        ai4 = findViewById(R.id.textViewPar5);
        ai5 = findViewById(R.id.textViewPar6);

        btnExit = findViewById(R.id.btnExitConnection);

        btn1 = findViewById(R.id.buttonDevice1);
        btn2 = findViewById(R.id.buttonDevice2);
        btn3 = findViewById(R.id.buttonDevice3);
        btn4 = findViewById(R.id.buttonDevice4);
        btn5 = findViewById(R.id.buttonDevice5);

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
        MyCustomInterface i = this;
        Context context = this;
            mConnect = new Connection(cbHost, Integer.parseInt(cbPort), context, i);
            // Открытие сокета в отдельном потоке
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mConnect.openConnection();
                        indicator.setBackgroundColor(backgroundColor);
                        mConnect.run();
                        Log.d(Connection.LOG_TAG, "Соединение установлено");

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
                if(isChecked) onSendClick("DO0","1"); else onSendClick("DO0","0");
            }
        });
        tb1.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("DO1","1"); else onSendClick("DO1","0");
            }
        });
        tb2.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("DO2","1"); else onSendClick("DO2","0");
            }
        });
        tb3.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("DO3","1"); else onSendClick("DO3","0");
            }
        });
        tb4.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("DO4","1"); else onSendClick("DO4","0");
            }
        });
        tb5.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("DO5","1"); else onSendClick("DO5","0");
            }
        });
        tb6.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("DO6","1"); else onSendClick("DO6","0");
            }
        });
        tb7.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("DO7","1"); else onSendClick("DO7","0");
            }
        });
        tb8.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("DO8","1"); else onSendClick("DO8","0");
            }
        });
        tb9.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("DO9","1"); else onSendClick("DO9","0");
            }
        });
        tb10.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("DO10","1"); else onSendClick("DO10","0");
            }
        });
        tb11.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("DO11","1"); else onSendClick("DO11","0");
            }
        });
        tb12.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("DO12","1"); else onSendClick("DO12","0");
            }
        });
        tb13.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("DO13","1"); else onSendClick("DO13","0");
            }
        });
        tb14.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("DO14","1"); else onSendClick("DO14","0");
            }
        });
        tb15.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
            {
                if(isChecked) onSendClick("DO15","1"); else onSendClick("DO15","0");
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                onCloseClick();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                devCurr = 1;
                setDevData(1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                devCurr = 2;
                setDevData(2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                devCurr = 3;
                setDevData(3);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                devCurr = 4;
                setDevData(4);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                devCurr = 5;
                setDevData(5);
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
        String strN;
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
                        switch (devCurr){
                            case 1 : jo.put("Device", "A1"); break;
                            case 2 : jo.put("Device", "A2"); break;
                            case 3 : jo.put("Device", "A3"); break;
                            case 4 : jo.put("Device", "A4"); break;
                            case 5 : jo.put("Device", "A5"); break;
                        }
                        jo.put(text1, text2);
                        /* отправляем на сервер данные */
                        mConnect.sendData(jo.toString().getBytes());
                    } catch (Exception e) {
                        Log.e(Connection.LOG_TAG, e.getMessage());
                    }
                }
            }).start();
        }
    }

   @Override
    public void sendData1(String str) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    jsonReader(str);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    public void setDevData(int numDev){
        int n = numDev-1;
        name.setText(myDevices[n].getDevice());
        ready.setText(myDevices[n].getStatus());
        ai0.setText(myDevices[n].getAI0());
        ai1.setText(myDevices[n].getAI1());
        ai2.setText(myDevices[n].getAI2());
        ai3.setText(myDevices[n].getAI3());
        ai4.setText(myDevices[n].getAI4());
        ai5.setText(myDevices[n].getAI5());

        if(myDevices[n].getDO0().equals("1")) tb0.setChecked(true); else tb0.setChecked(false);
        if(myDevices[n].getDO1().equals("1")) tb1.setChecked(true); else tb1.setChecked(false);
        if(myDevices[n].getDO2().equals("1")) tb2.setChecked(true); else tb2.setChecked(false);
        if(myDevices[n].getDO3().equals("1")) tb3.setChecked(true); else tb3.setChecked(false);
        if(myDevices[n].getDO4().equals("1")) tb4.setChecked(true); else tb4.setChecked(false);
        if(myDevices[n].getDO5().equals("1")) tb5.setChecked(true); else tb5.setChecked(false);
        if(myDevices[n].getDO6().equals("1")) tb6.setChecked(true); else tb6.setChecked(false);
        if(myDevices[n].getDO7().equals("1")) tb7.setChecked(true); else tb7.setChecked(false);
        if(myDevices[n].getDO8().equals("1")) tb8.setChecked(true); else tb8.setChecked(false);
        if(myDevices[n].getDO9().equals("1")) tb9.setChecked(true); else tb9.setChecked(false);
        if(myDevices[n].getDO10().equals("1")) tb10.setChecked(true); else tb10.setChecked(false);
        if(myDevices[n].getDO11().equals("1")) tb11.setChecked(true); else tb11.setChecked(false);
        if(myDevices[n].getDO12().equals("1")) tb12.setChecked(true); else tb12.setChecked(false);
        if(myDevices[n].getDO13().equals("1")) tb13.setChecked(true); else tb13.setChecked(false);
        if(myDevices[n].getDO14().equals("1")) tb14.setChecked(true); else tb14.setChecked(false);
        if(myDevices[n].getDO15().equals("1")) tb15.setChecked(true); else tb15.setChecked(false);

        if(myDevices[n].getDI0().equals("1")) stb0.setChecked(true); else stb0.setChecked(false);
        if(myDevices[n].getDI1().equals("1")) stb1.setChecked(true); else stb1.setChecked(false);
        if(myDevices[n].getDI2().equals("1")) stb2.setChecked(true); else stb2.setChecked(false);
        if(myDevices[n].getDI3().equals("1")) stb3.setChecked(true); else stb3.setChecked(false);
        if(myDevices[n].getDI4().equals("1")) stb4.setChecked(true); else stb4.setChecked(false);
        if(myDevices[n].getDI5().equals("1")) stb5.setChecked(true); else stb5.setChecked(false);
        if(myDevices[n].getDI6().equals("1")) stb6.setChecked(true); else stb6.setChecked(false);
        if(myDevices[n].getDI7().equals("1")) stb7.setChecked(true); else stb7.setChecked(false);
        if(myDevices[n].getDI8().equals("1")) stb8.setChecked(true); else stb8.setChecked(false);
        if(myDevices[n].getDI9().equals("1")) stb9.setChecked(true); else stb9.setChecked(false);
        if(myDevices[n].getDI10().equals("1")) stb10.setChecked(true); else stb10.setChecked(false);
        if(myDevices[n].getDI11().equals("1")) stb11.setChecked(true); else stb11.setChecked(false);
        if(myDevices[n].getDI12().equals("1")) stb12.setChecked(true); else stb12.setChecked(false);
        if(myDevices[n].getDI13().equals("1")) stb13.setChecked(true); else stb13.setChecked(false);
        if(myDevices[n].getDI14().equals("1")) stb14.setChecked(true); else stb14.setChecked(false);
        if(myDevices[n].getDI15().equals("1")) stb15.setChecked(true); else stb15.setChecked(false);
    }
    public  void  jsonReader(String str) throws JSONException {
        JSONObject json = new JSONObject(str);
        JSONArray urls = json.getJSONArray("data");
        int num=0;
        String s;
        for (int i = 0; i < urls.length(); i++) {
            s = urls.getJSONObject(i).getString("Device").toString();
            if (s.equals("A1")) {
                num = 0;
                myDevices[0]=new DeviceClass();
                btn1.setVisibility(VISIBLE);
            } else if (s.equals("A2")) {
                num = 1;
                myDevices[1]=new DeviceClass();
                btn2.setVisibility(VISIBLE);
            }else if (s.equals("A3")) {
                num = 2;
                myDevices[2]=new DeviceClass();
                btn3.setVisibility(VISIBLE);
            }else if (s.equals("A4")) {
                num = 3;
                myDevices[3]=new DeviceClass();
                btn4.setVisibility(VISIBLE);
            }else if (s.equals("A5")) {
                num = 4;
                myDevices[4]=new DeviceClass();
                btn5.setVisibility(VISIBLE);
            }
            myDevices[num].Device = s;
            myDevices[num].Name = urls.getJSONObject(i).getString("Name").toString();
            myDevices[num].Status = urls.getJSONObject(i).getString("Status").toString();
            myDevices[num].DO0 = urls.getJSONObject(i).getString("DO0").toString();
            myDevices[num].DO1 = urls.getJSONObject(i).getString("DO1").toString();
            myDevices[num].DO2 = urls.getJSONObject(i).getString("DO2").toString();
            myDevices[num].DO3 = urls.getJSONObject(i).getString("DO3").toString();
            myDevices[num].DO4 = urls.getJSONObject(i).getString("DO4").toString();
            myDevices[num].DO5 = urls.getJSONObject(i).getString("DO5").toString();
            myDevices[num].DO6 = urls.getJSONObject(i).getString("DO6").toString();
            myDevices[num].DO7 = urls.getJSONObject(i).getString("DO7").toString();
            myDevices[num].DO8 = urls.getJSONObject(i).getString("DO8").toString();
            myDevices[num].DO9 = urls.getJSONObject(i).getString("DO9").toString();
            myDevices[num].DO10 = urls.getJSONObject(i).getString("DO10").toString();
            myDevices[num].DO11 = urls.getJSONObject(i).getString("DO11").toString();
            myDevices[num].DO12 = urls.getJSONObject(i).getString("DO12").toString();
            myDevices[num].DO13 = urls.getJSONObject(i).getString("DO13").toString();
            myDevices[num].DO14 = urls.getJSONObject(i).getString("DO14").toString();
            myDevices[num].DO15 = urls.getJSONObject(i).getString("DO15").toString();

            myDevices[num].DI0 = urls.getJSONObject(i).getString("DI0").toString();
            myDevices[num].DI1 = urls.getJSONObject(i).getString("DI1").toString();
            myDevices[num].DI2 = urls.getJSONObject(i).getString("DI2").toString();
            myDevices[num].DI3 = urls.getJSONObject(i).getString("DI3").toString();
            myDevices[num].DI4 = urls.getJSONObject(i).getString("DI4").toString();
            myDevices[num].DI5 = urls.getJSONObject(i).getString("DI5").toString();
            myDevices[num].DI6 = urls.getJSONObject(i).getString("DI6").toString();
            myDevices[num].DI7 = urls.getJSONObject(i).getString("DI7").toString();
            myDevices[num].DI8 = urls.getJSONObject(i).getString("DI8").toString();
            myDevices[num].DI9 = urls.getJSONObject(i).getString("DI9").toString();
            myDevices[num].DI10 = urls.getJSONObject(i).getString("DI10").toString();
            myDevices[num].DI11 = urls.getJSONObject(i).getString("DI11").toString();
            myDevices[num].DI12 = urls.getJSONObject(i).getString("DI12").toString();
            myDevices[num].DI13 = urls.getJSONObject(i).getString("DI13").toString();
            myDevices[num].DI14 = urls.getJSONObject(i).getString("DI14").toString();
            myDevices[num].DI15 = urls.getJSONObject(i).getString("DI15").toString();

            myDevices[num].AI0 = urls.getJSONObject(i).getString("AI0").toString();
            myDevices[num].AI1 = urls.getJSONObject(i).getString("AI1").toString();
            myDevices[num].AI2 = urls.getJSONObject(i).getString("AI2").toString();
            myDevices[num].AI3 = urls.getJSONObject(i).getString("AI3").toString();
            myDevices[num].AI4 = urls.getJSONObject(i).getString("AI4").toString();
            myDevices[num].AI5 = urls.getJSONObject(i).getString("AI5").toString();
        }
        setDevData(devCurr);
    }
}