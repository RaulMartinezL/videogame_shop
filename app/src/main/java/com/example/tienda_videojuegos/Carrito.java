package com.example.tienda_videojuegos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Carrito extends AppCompatActivity {

    List<String> gameTitle = new ArrayList<String>();
    List<String> gamePrice = new ArrayList<String>();


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private Button NextProcessBtn;
    private TextView txtTotalAmount;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        listView = findViewById(R.id.carritoDeLaCompra);


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
                        Intent intentPS4 = new Intent(Carrito.this, PS4Games.class);
                        startActivity(intentPS4);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_xbox:
                        item.setChecked(true);
                        Intent intentXbox = new Intent(Carrito.this, XboxGames.class);
                        startActivity(intentXbox);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_ofertas:
                        item.setChecked(true);
                        Intent intentOfertas = new Intent(Carrito.this, OfertasGames.class);
                        startActivity(intentOfertas);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_novedades:
                        item.setChecked(true);
                        Intent intentNovedades = new Intent(Carrito.this, NovedadesGames.class);
                        startActivity(intentNovedades);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_contacto:
                        item.setChecked(true);
                        Intent intentContacto = new Intent(Carrito.this, ContactoSelect.class);
                        startActivity(intentContacto);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_donde_estamos:
                        item.setChecked(true);
                        Intent intentGPS = new Intent(Carrito.this, MapsActivity.class);
                        startActivity(intentGPS);
                        drawerLayout.closeDrawers();
                        return true;
                }
                return false;
            }
        });




        //recyclerView = findViewById(R.id.cat_list);
       // recyclerView.setHasFixedSize(true);
        //layoutManager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(layoutManager);

       // NextProcessBtn = (Button) findViewById(R.id.next_process_btn);
        //txtTotalAmount = (TextView) findViewById(R.id.total_price);



        // ESTO ES NULL ARREGLAR MAÑANA
        gameTitle.add(getIntent().getStringExtra("GameTitleCart"));
        gamePrice.add(getIntent().getStringExtra("GamePriceCart"));

        Log.d("TAG", String.valueOf(gamePrice));
        Log.d("TAG", String.valueOf(gameTitle));



        MyAdapter adapter = new MyAdapter(this, gameTitle, gamePrice);
        listView.setAdapter(adapter);

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
        String[] gamePrice;


        MyAdapter(Context c, List<String> title, List<String> price){
            super(c, R.layout.row_carrito_compra, R.id.gameTitle, title);
            this.context = c;
            this.gameTitle = title.toArray(new String[0]);
            this.gamePrice = price.toArray(new String[0]);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row_carrito_compra, parent, false);

            TextView myTitle = row.findViewById(R.id.gameTitleCarrito);
            TextView mPrice = row.findViewById(R.id.gamePriceCarrito);

            myTitle.setText(gameTitle[position]);
            mPrice.setText(gamePrice[position]);

            return row;
        }
    }




}
