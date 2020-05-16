package com.example.tienda_videojuegos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class Contactar extends AppCompatActivity {


    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactar);

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
                        Intent intentPS4 = new Intent(Contactar.this, PS4Games.class);
                        startActivity(intentPS4);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_xbox:
                        item.setChecked(true);
                        Intent intentXbox = new Intent(Contactar.this, XboxGames.class);
                        startActivity(intentXbox);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_ofertas:
                        item.setChecked(true);
                        Intent intentOfertas = new Intent(Contactar.this, OfertasGames.class);
                        startActivity(intentOfertas);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_novedades:
                        item.setChecked(true);
                        Intent intentNovedades = new Intent(Contactar.this, NovedadesGames.class);
                        startActivity(intentNovedades);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_contacto:
                        item.setChecked(true);
                        Intent intentContacto = new Intent(Contactar.this, ContactoSelect.class);
                        startActivity(intentContacto);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_donde_estamos:
                        item.setChecked(true);
                        Intent intentGPS = new Intent(Contactar.this, GPS.class);
                        startActivity(intentGPS);
                        drawerLayout.closeDrawers();
                        return true;
                }
                return false;
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

}
