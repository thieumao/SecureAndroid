package com.example.thanhtung.myapplication;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onShowPinButton(View v){
        Toast.makeText(this, "Clicked on Button 1", Toast.LENGTH_LONG).show();

        Uri myURI = Uri.parse("content://com.els.pwdmanager.contentprovider/pwds");
        String[] cols = new String[] { "_id", "name", "pwd"};
        ContentResolver cr = getContentResolver();
        Cursor c = cr.query(myURI, cols, null, null,null,null);
        c.moveToFirst();
        String str = c.getString(2);
        Log.e("str =", "str = " + str);

        Utility util = new Utility();
        String result = util.decrypt(str);

        Log.e("str =", "result = " + result);

        TextView pinTextView = (TextView) findViewById(R.id.pinTextView);
        pinTextView.setText("Pin: " + result);
    }

    public void onShowSafeNoteButton(View v) {
        Toast.makeText(this, "Clicked on Button 2", Toast.LENGTH_LONG).show();
        Uri myURI = Uri.parse("content://com.els.safenote/Congratulations.txt");
        File file = new File(myURI.getPath());
        String str = readText(file);
        Log.e("mao", "mao = " + str);
    }


    private String readText(File f) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i;
        try {
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString();
    }


}
