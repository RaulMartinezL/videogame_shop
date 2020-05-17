package com.example.tienda_videojuegos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tienda_videojuegos.db.NoteDatabase;
import com.example.tienda_videojuegos.db.Videojuego;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class OfertasGames extends AppCompatActivity {


    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    ListView listView;

    List<String> gameTitle = new ArrayList<String>();
    List<String> gameDescription = new ArrayList<String>();
    List<String> gamePrice = new ArrayList<String>();
    List<String> gamePlatform = new ArrayList<String>();
    List<String> gameDate = new ArrayList<String>();
    List<String> gameSale = new ArrayList<String>();

    int[] gamePicture = {R.drawable.m9, R.drawable.m9, R.drawable.m9, R.drawable.m9, R.drawable.m9, R.drawable.m9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertas_games);
        listView = findViewById(R.id.ofertasGame);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout_ofertas);
        navigationView = findViewById(R.id.navigationView);

        final ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.nav_ps4:
                        item.setChecked(true);
                        Intent intentPS4 = new Intent(OfertasGames.this, PS4Games.class);
                        startActivity(intentPS4);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_xbox:
                        item.setChecked(true);
                        Intent intentXbox = new Intent(OfertasGames.this, XboxGames.class);
                        startActivity(intentXbox);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_ofertas:
                        item.setChecked(true);
                        Intent intentOfertas = new Intent(OfertasGames.this, OfertasGames.class);
                        startActivity(intentOfertas);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_novedades:
                        item.setChecked(true);
                        Intent intentNovedades = new Intent(OfertasGames.this, NovedadesGames.class);
                        startActivity(intentNovedades);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_contacto:
                        item.setChecked(true);
                        Intent intentContacto = new Intent(OfertasGames.this, ContactoSelect.class);
                        startActivity(intentContacto);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_donde_estamos:
                        item.setChecked(true);
                        Intent intentGPS = new Intent(OfertasGames.this, MapsActivity.class);
                        startActivity(intentGPS);
                        drawerLayout.closeDrawers();
                        return true;
                }
                return false;
            }
        });



        SQLiteOpenHelper gameDatabase = new NoteDatabase(getApplicationContext());
        Videojuego foo = new Videojuego(gameDatabase);
        String query = "SELECT * FROM GAMES WHERE sale = 'true' ";
        ArrayList<List<String>> ofertasGames = foo.getData(query);

        for (int i = 0; i < ofertasGames.size(); i++){
            gameTitle.add(ofertasGames.get(i).get(0));
            gameDescription.add(ofertasGames.get(i).get(1));
            gamePlatform.add(ofertasGames.get(i).get(2));
            gamePrice.add(ofertasGames.get(i).get(3));
            gameDate.add(ofertasGames.get(i).get(4));
            gameSale.add(ofertasGames.get(i).get(5));
        }

        MyAdapter adapter = new MyAdapter(this, gameTitle, gameDescription, gamePicture,  gamePrice, gamePlatform);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), GameDetail.class);
                intent.putExtra("GameTitle", gameTitle.get(position));
                intent.putExtra("GameDescription", gameDescription.get(position));
                intent.putExtra("GameImage", gamePicture[position]);
                intent.putExtra("GamePrice", gamePrice.get(position));
                intent.putExtra("GamePrice", gamePlatform.get(position));
                intent.putExtra("GamePrice", gameDate.get(position));
                intent.putExtra("GamePrice", gameSale.get(position));
                startActivity(intent);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }




    class MyAdapter extends ArrayAdapter<String> {


        Context context;
        String[] gameTitle;
        String[] gameDescription;
        String[] gamePrice;
        String[] gamePlatform;
        int[] gamePicture;

        MyAdapter(Context c, List<String> title, List<String> description, int[] imgs,  List<String> price, List<String>  platform){
            super(c, R.layout.row_game, R.id.gameTitle, title);
            this.context = c;
            this.gameTitle = title.toArray(new String[0]);
            this.gameDescription = description.toArray(new String[0]);
            this.gamePicture = imgs;
            this.gamePrice = price.toArray(new String[0]);
            this.gamePlatform = platform.toArray(new String[0]);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row_game, parent, false);
            ImageView images = row.findViewById(R.id.gameImage);
            TextView myTitle = row.findViewById(R.id.gameTitle);
            TextView myYear = row.findViewById(R.id.gameDescription);
            TextView mPrice = row.findViewById(R.id.gamePrice);
            TextView mPlatform = row.findViewById(R.id.gamePlatform);

            images.setImageResource(gamePicture[position]);
            myTitle.setText(gameTitle[position]);
            myYear.setText(gameDescription[position]);
            mPrice.setText(gamePrice[position]);
            mPlatform.setText(gamePlatform[position]);

            return row;
        }
    }



}
