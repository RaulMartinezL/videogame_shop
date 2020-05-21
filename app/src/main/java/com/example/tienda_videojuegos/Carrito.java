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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.prefs.PreferenceChangeEvent;

public class Carrito extends AppCompatActivity {

    static List<String> gameTitle = new ArrayList<String>();
    static List<String> gamePrice = new ArrayList<String>();


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private MaterialButton NextProcessBtn;
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

                    case R.id.nav_carrito:
                        item.setChecked(true);
                        Intent intentCarrito = new Intent(Carrito.this, Carrito.class);

                        startActivity(intentCarrito);
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



        // ESTO ES NULL ARREGLAR MAÑANA
        //Intent intent = getIntent();
        //gameTitle.add(intent.getStringExtra("GameTitleCart"));
        //gamePrice.add(intent.getStringExtra("GamePriceCart"));



        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Map<String, ?> allEntries = prefs.getAll();
        for(Map.Entry<String, ?> entry : allEntries.entrySet()){
            Log.d("Map values", entry.getKey() + ": " + entry.getValue().toString());
            if(entry.getKey() == "GameTitleCart"){
                gameTitle.add(entry.getValue().toString());
            }
            else if (entry.getKey() == "GamePriceCart"){
                gamePrice.add(entry.getValue().toString());
            }
        }


        String titleAux = prefs.getString("GameTitleCart", "no id");
        String priceAux = prefs.getString("GamePriceCart", "no id");
        gameTitle.add(titleAux);
        gamePrice.add(priceAux);


        Log.d("TAG", String.valueOf(gameTitle));
        Log.d("TAG", String.valueOf(gamePrice));


        MyAdapter adapter = new MyAdapter(this, gameTitle, gamePrice);
        listView.setAdapter(adapter);


        MaterialButton botonContacto =  findViewById(R.id.boton_finalizar_compra);
        botonContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                gameTitle.clear();
                gamePrice.clear();

                Intent intentCarrito = new Intent(Carrito.this, ConfirmarPedido.class);
                startActivity(intentCarrito);
            }
        });







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
