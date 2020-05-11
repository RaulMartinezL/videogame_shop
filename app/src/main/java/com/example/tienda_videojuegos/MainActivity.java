package com.example.tienda_videojuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.tienda_videojuegos.db.NoteDatabase;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteOpenHelper gameDatabase = new NoteDatabase(getApplicationContext());

        SQLiteDatabase db = gameDatabase.getReadableDatabase();

        Cursor cursor = db.query("GAMES",
                new String[] {"_id", "NAME"},
                null,
                null,
                null, null, null);






















        String[] consoleNames = {"ps4", "xbox"};
        ListView listView = (ListView) findViewById(R.id.consolas);

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, consoleNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position==0)
                {
                    Intent intent = new Intent(MainActivity.this, PS4Games.class);
                    startActivity(intent);
                }
                if (position==1)
                {
                    Intent intent = new Intent(MainActivity.this, XboxGames.class);
                    startActivity(intent);
                }
            }
        });
    }


}
