package com.example.tienda_videojuegos.notedb;

import android.content.Context;
import android.provider.ContactsContract;

import java.io.IOException;
import java.io.InputStream;

public class Database {


    public String loadJSONFromAsset(Context context){
        String json = null;

        try{
            InputStream is = context.getResources().getAssets().open("database.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

}
