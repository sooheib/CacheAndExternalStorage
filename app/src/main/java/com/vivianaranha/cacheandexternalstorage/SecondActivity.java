package com.vivianaranha.cacheandexternalstorage;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        et = (EditText) findViewById(R.id.theDataLoad);

    }

    public void loadInternalCache(View v) {
        File folder = getCacheDir();
        File myFile = new File(folder, "myData1.txt");
        String data = readData(myFile);
        if(data != null){
            et.setText(data);
        } else {
            et.setText("No data was returned");
        }

    }

    public void loadExternalCache(View v) {
        File folder = getExternalCacheDir();
        File myFile = new File(folder, "myData2.txt");
        String data = readData(myFile);
        if(data != null){
            et.setText(data);
        } else {
            et.setText("No data was returned");
        }
    }

    public void loadPrivateExternalFile(View v) {
        File folder = getExternalFilesDir("MyPrivateData");
        File myFile = new File(folder, "myData3.txt");
        String data = readData(myFile);
        if(data != null){
            et.setText(data);
        } else {
            et.setText("No data was returned");
        }
    }

    public void loadPublicExternalFile(View v) {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(folder, "myData4.txt");
        String data = readData(myFile);
        if(data != null){
            et.setText(data);
        } else {
            et.setText("No data was returned");
        }
    }

    private String readData(File myFile){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myFile);
            int read = -1;
            StringBuffer buffer = new StringBuffer();
            while((read = fileInputStream.read()) != -1){
                buffer.append((char)read);
            }
            return buffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
