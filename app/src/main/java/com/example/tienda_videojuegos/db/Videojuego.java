package com.example.tienda_videojuegos.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Videojuego {


    private SQLiteOpenHelper gameDatabase;

    public Videojuego(SQLiteOpenHelper database){

        this.gameDatabase = database;

    }


    public ArrayList<List<String>> getData(String query){
        ArrayList<List<String>> data = new ArrayList<>();

        SQLiteDatabase db = this.gameDatabase.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do {
                ArrayList<String> innerStringList = new ArrayList<>();

                innerStringList.add(cursor.getString(cursor.getColumnIndex("NAME")));
                innerStringList.add(cursor.getString(cursor.getColumnIndex("DESCRIPTION")));
                innerStringList.add(cursor.getString(cursor.getColumnIndex("PLATFORM")));
                innerStringList.add(cursor.getString(cursor.getColumnIndex("PRICE")));
                innerStringList.add(cursor.getString(cursor.getColumnIndex("DATE")));
                innerStringList.add(cursor.getString(cursor.getColumnIndex("SALE")));

                data.add(innerStringList);
                Log.d("innerList name tiene:", innerStringList.get(0));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return data;

    }

}
