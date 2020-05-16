package com.example.tienda_videojuegos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
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


import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.tienda_videojuegos.db.NoteDatabase;
import com.example.tienda_videojuegos.db.Videojuego;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

// TODO: home activity button pulsando en el texto
// TODO: carrito de la compra
// TODO: arreglar la query de ofertas
// TODO: ofertas gestionarlas con fechas
// TODO: Contactar Toast de comprobacion del mail
// TODO: Edit text boxes que no cambien de tamaño o limitar un tamaño maximo de caracteres.



public class MainActivity extends AppCompatActivity {

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


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.novedadesGamesMain);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
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
                        Intent intentPS4 = new Intent(MainActivity.this, PS4Games.class);
                        startActivity(intentPS4);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_xbox:
                        item.setChecked(true);
                        Intent intentXbox = new Intent(MainActivity.this, XboxGames.class);
                        startActivity(intentXbox);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_ofertas:
                        item.setChecked(true);
                        Intent intentOfertas = new Intent(MainActivity.this, OfertasGames.class);
                        startActivity(intentOfertas);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_novedades:
                        item.setChecked(true);
                        Intent intentNovedades = new Intent(MainActivity.this, NovedadesGames.class);
                        startActivity(intentNovedades);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_contacto:
                        item.setChecked(true);
                        Intent intentContacto = new Intent(MainActivity.this, ContactoSelect.class);
                        startActivity(intentContacto);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_donde_estamos:
                        item.setChecked(true);
                        Intent intentGPS = new Intent(MainActivity.this, MapsActivity.class);

                        startActivity(intentGPS);
                        drawerLayout.closeDrawers();
                        return true;
                }
                return false;
            }
        });

        SQLiteOpenHelper gameDatabase = new NoteDatabase(getApplicationContext());
        Videojuego foo = new Videojuego(gameDatabase);
        String query = "SELECT * FROM GAMES  WHERE date = 'true' ";
        ArrayList<List<String>> novedadesGames = foo.getData(query);


        for (int i = 0; i < novedadesGames.size(); i++){
            gameTitle.add(novedadesGames.get(i).get(0));
            gameDescription.add(novedadesGames.get(i).get(1));
            gamePrice.add(novedadesGames.get(i).get(2));
            gamePlatform.add(novedadesGames.get(i).get(3));
            gameDate.add(novedadesGames.get(i).get(4));
            gameSale.add(novedadesGames.get(i).get(5));
        }

        MyAdapter adapter1 = new MyAdapter(this, gameTitle, gameDescription, gamePicture);
        listView.setAdapter(adapter1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), GameDetail.class);
                intent.putExtra("GameTitle", gameTitle.get(position));
                intent.putExtra("GameDescription", gameDescription.get(position));
                intent.putExtra("GameImage", gamePicture[position]);
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
        int[] gamePicture;

        MyAdapter(Context c, List<String> title, List<String> description, int[] imgs){
            super(c, R.layout.row_game, R.id.gameTitle, title);
            this.context = c;
            this.gameTitle = title.toArray(new String[0]);
            this.gameDescription = description.toArray(new String[0]);
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
