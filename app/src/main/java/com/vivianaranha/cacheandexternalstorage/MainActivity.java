package com.vivianaranha.cacheandexternalstorage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.theData);
    }

    public void goToNext(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void saveInternalCache(View v) {
        String theData = et.getText().toString();
        File folder = getCacheDir();
        File myFile = new File(folder, "myData1.txt");
        writeData(myFile, theData);

    }

    public void saveExternalCache(View v) {
        String theData = et.getText().toString();
        File folder = getExternalCacheDir();
        File myFile = new File(folder, "myData2.txt");
        writeData(myFile, theData);
    }

    public void saveExternalPrivate(View v) {
        String theData = et.getText().toString();
        if(getExternalFilesDir("MyPrivateData") != null) {
            File folder = getExternalFilesDir("MyPrivateData");
            File myFile = new File(folder, "myData3.txt");
            writeData(myFile, theData);
        }
    }

    public void saveExternalPublic(View v) {
        String theData = et.getText().toString();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(folder, "myData4.txt");
        writeData(myFile, theData);
    }

    private void writeData(File myFile, String theData){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(myFile);
            fileOutputStream.write(theData.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
