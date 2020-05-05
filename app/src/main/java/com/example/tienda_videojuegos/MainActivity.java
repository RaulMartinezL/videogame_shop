package com.example.tienda_videojuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
