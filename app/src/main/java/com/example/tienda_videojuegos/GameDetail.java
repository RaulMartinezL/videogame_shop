package com.example.tienda_videojuegos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class GameDetail extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        String albumTitle = getIntent().getStringExtra("GameTitle");
        String albumDescription = getIntent().getStringExtra("GameDescription");
        int albumImage = (int) Objects.requireNonNull(getIntent().getExtras().get("GameImage"));

        ((TextView)findViewById(R.id.textViewTitle)).setText(albumTitle);
        ((TextView)findViewById(R.id.textViewDescription)).setText(albumDescription);
        ImageView photo = findViewById(R.id.imageView);
        photo.setImageResource(albumImage);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
