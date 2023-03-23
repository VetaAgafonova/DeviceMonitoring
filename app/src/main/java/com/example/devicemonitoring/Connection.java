package com.example.devicemonitoring;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ToggleButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Connection implements Runnable
{
    private transient  Socket  mSocket = null;
    private  String  mHost   = null;
    private  int     mPort   = 0;
    private InputStream inputStream = null;

    public static final String LOG_TAG = "SOCKET";
    Context mContext = null;
    private MyCustomInterface i = null;



    public Connection() {}
    public Connection (MyCustomInterface i){
        this.i = i;
    }

    public Connection (final String host, final int port, Context context, MyCustomInterface a)
    {
        this.mHost = host;
        this.mPort = port;
        this.mContext = context;
        this.i = a;


    }

    // Метод открытия сокета
    public void openConnection() throws Exception
    {
        // Если сокет уже открыт, то он закрывается
        closeConnection();
        try {
            // Создание сокета
            mSocket = new Socket(mHost, mPort);
        } catch (IOException e) {
            throw new Exception("Невозможно создать сокет: "
                    + e.getMessage());
        }
    }
    //Метод закрытия сокета
    public void closeConnection()
    {
        if (mSocket != null && !mSocket.isClosed()) {
            try {
                mSocket.close();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Ошибка при закрытии сокета :"
                        + e.getMessage());
            } finally {
                mSocket = null;
            }
        }
        mSocket = null;
    }

    //Метод отправки данных
    public void sendData(byte[] data) throws Exception {
        // Проверка открытия сокета
        if (mSocket == null || mSocket.isClosed()) {
            throw new Exception("Ошибка отправки данных. " +
                    "Сокет не создан или закрыт");
        }
        // Отправка данных
        try {
            mSocket.getOutputStream().write(data);
            mSocket.getOutputStream().flush();
        } catch (IOException e) {
            throw new Exception("Ошибка отправки данных : "
                    + e.getMessage());
        }
    }

    @Override
    protected void finalize() throws Throwable
    {
        super.finalize();
        closeConnection();
    }

    @Override
    public void run() {
        try {
            // Определение входного потока
            inputStream = mSocket.getInputStream();
        } catch (IOException e) {
            System.err.println("Can't get input stream");
        }
        // Буфер для чтения информации
        byte[] data = new byte[1024*4];
        while(true) {
            try {
                /*
                 * Получение информации :
                 *    count - количество полученных байт
                 */
                int count;
                count = inputStream.read(data,0,data.length);

                if (count > 0) {
                    String msg = new String(data, 0, count);
                    JSONObject js = new JSONObject(msg);
                    System.out.println(msg);
                   if(i != null) i.sendData1(msg);
                } else if (count == -1 ) {
                    // Если count=-1, то поток прерван
                    System.out.println("1socket is closed");
                    mSocket.close();
                    break;
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("1ConnectionWorker stoped");
    }
}
