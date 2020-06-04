package com.example.tienda_videojuegos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.BitmapFactory;
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



// TODO: hacer funcionar el boton de la app bar de la tienda



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
        String query_novedades = "SELECT * FROM GAMES  WHERE date = 'true' ";
        ArrayList<List<String>> novedadesGames = foo.getData(query_novedades);


        for (int i = 0; i < novedadesGames.size(); i++){
            gameTitle.add(novedadesGames.get(i).get(0));
            gameDescription.add(novedadesGames.get(i).get(1));
            gamePlatform.add(novedadesGames.get(i).get(2));
            gamePrice.add(novedadesGames.get(i).get(3));
            gameDate.add(novedadesGames.get(i).get(4));
            gameSale.add(novedadesGames.get(i).get(5));
        }

        MyAdapter adapter1 = new MyAdapter(this, gameTitle, gameDescription, gamePicture, gamePrice, gamePlatform);
        listView.setAdapter(adapter1);

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
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item_shopping_cart = menu.findItem(R.id.nav_boton_tienda);
        item_shopping_cart.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intentCarrito = new Intent(MainActivity.this, Carrito.class);
                startActivity(intentCarrito);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
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

        MyAdapter(Context c, List<String> title, List<String> description, int[] imgs, List<String> price, List<String>  platform){
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
