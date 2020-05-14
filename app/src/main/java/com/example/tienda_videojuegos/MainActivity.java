package com.example.tienda_videojuegos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.tienda_videojuegos.db.NoteDatabase;



public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);





        String[] consoleNames = {"ps4", "xbox", "novedades", " ofertas"};
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
                if (position==2)
                {
                    Intent intent = new Intent(MainActivity.this, NovedadesGames.class);
                    startActivity(intent);
                }
                if (position==3)
                {
                    Intent intent = new Intent(MainActivity.this, OfertasGames.class);
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
