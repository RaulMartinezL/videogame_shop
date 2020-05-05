package com.example.tienda_videojuegos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class GameDetail extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);


        String albumTitle = getIntent().getStringExtra("AlbumTitle");
        String albumDescription = getIntent().getStringExtra("AlbumDescription");
        int albumImage = (int) Objects.requireNonNull(getIntent().getExtras().get("AlbumImage"));

        ((TextView)findViewById(R.id.textViewTitle)).setText(albumTitle);
        ((TextView)findViewById(R.id.textViewDescription)).setText(albumDescription);
        ImageView photo = findViewById(R.id.imageView);
        photo.setImageResource(albumImage);
    }
}
