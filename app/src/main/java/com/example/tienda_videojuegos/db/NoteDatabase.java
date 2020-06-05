package com.example.tienda_videojuegos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class NoteDatabase extends SQLiteOpenHelper {

    private static final String DBNAME = "gamesdatabase";
    private static final int DBVERSION = 1;
    private  Context context = null;


    public NoteDatabase(Context context) {
        super(context, DBNAME, null, DBVERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE GAMES ("
                + "NAME TEXT, "
                + "DESCRIPTION TEXT,"
                + "PRICE TEXT,"
                + "PLATFORM TEXT,"
                + "DATE TEXT,"
                + "SALE TEXT);");


        addVideogame(db, "xbox1", "xbox1 game description", "10", "xbox", "true", "true");
        addVideogame(db, "xbox2", "xbox2 game description", "10", "xbox", "true", "true");
        addVideogame(db, "xbox3", "xbox3 game description", "10", "xbox", "true", "true");
        addVideogame(db, "ps4_1", "ps4_1 game description", "10", "ps4", "true", "true");
        addVideogame(db, "ps4_2", "ps4_2 game description", "10", "ps4", "true", "true");
        addVideogame(db, "ps4_3", "ps4_3 game description", "10", "ps4", "true", "true");


        //loadDatabase(db, context);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void addVideogame(SQLiteDatabase db, String name, String description, String price,
                                     String platform, String date, String sale) {

        ContentValues gamesData = new ContentValues();
        gamesData.put("NAME", name);
        gamesData.put("DESCRIPTION", description);
        gamesData.put("PLATFORM", platform);
        gamesData.put("PRICE", price);
        gamesData.put("DATE", date);
        gamesData.put("SALE", sale);

        Log.d("TAG", String.valueOf(name));
        Log.d("TAG", "Cargamos datos");
        db.insert("GAMES", null, gamesData);
    }


    private void loadDatabase(SQLiteDatabase db, Context context) {

        try {
            String object = loadJSONFromAsset(context);
            JSONObject obj = new JSONObject(object);
            JSONArray m_jArray = obj.getJSONArray("games");

            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArray.length(); i++) {
                JSONObject jo_inside = m_jArray.getJSONObject(i);
                Log.d("Details->>", jo_inside.getString("name"));

                String game_name = jo_inside.getString("name");
                String game_description = jo_inside.getString("description");
                String game_price = jo_inside.getString("price");
                String game_platform = jo_inside.getString("platform");
                String game_date = jo_inside.getString("date");
                String game_onsale = jo_inside.getString("sale");

                addVideogame(db, game_name, game_description, game_price,
                        game_platform, game_date, game_onsale);
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    private String loadJSONFromAsset(Context context){
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
