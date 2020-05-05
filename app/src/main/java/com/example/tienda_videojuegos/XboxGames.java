package com.example.tienda_videojuegos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class XboxGames extends AppCompatActivity {

    ListView listView;

    String[] gameTitle = {"Xbox1", "Xbox2", "Xbox3"};

    String[] gameDescription = {"TLOU", "Cod4", "KH"};

    int[] gamePicture = {R.drawable.m9, R.drawable.m9, R.drawable.m9};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xbox_games);

        listView = findViewById(R.id.xboxGames);

        MyAdapter adapter = new MyAdapter(this, gameTitle, gameDescription, gamePicture);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), GameDetail.class);
                intent.putExtra("AlbumTitle", gameTitle[position]);
                intent.putExtra("AlbumDescription", gameDescription[position]);
                intent.putExtra("AlbumImage", gamePicture[position]);
                startActivity(intent);
            }
        });

    }

    class MyAdapter extends ArrayAdapter<String> {


        Context context;
        String[] gameTitle;
        String[] gameDescription;
        int[] gamePicture;

        MyAdapter(Context c, String title[], String description[], int imgs[]){
            super(c, R.layout.row_game, R.id.gameTitle, title);
            this.context = c;
            this.gameTitle = title;
            this.gameDescription = description;
            this.gamePicture = imgs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row_game, parent, false);
            ImageView images = row.findViewById(R.id.gameImage);
            TextView myTitle = row.findViewById(R.id.gameTitle);
            TextView myYear = row.findViewById(R.id.gameDescription);

            images.setImageResource(gamePicture[position]);
            myTitle.setText(gameTitle[position]);
            myYear.setText(gameDescription[position]);

            return row;
        }
    }
}
