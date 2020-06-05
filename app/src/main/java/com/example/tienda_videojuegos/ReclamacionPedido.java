package com.example.tienda_videojuegos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;


public class ReclamacionPedido extends AppCompatActivity {

    private static final int GALLERY_REQUEST = 1;
    private static final int RETURN_MAIN = -10;
    Uri uriImage = null;

    ImageView imageView;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamacion_pedido);


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
                        Intent intentPS4 = new Intent(ReclamacionPedido.this, PS4Games.class);
                        startActivity(intentPS4);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_xbox:
                        item.setChecked(true);
                        Intent intentXbox = new Intent(ReclamacionPedido.this, XboxGames.class);
                        startActivity(intentXbox);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_ofertas:
                        item.setChecked(true);
                        Intent intentOfertas = new Intent(ReclamacionPedido.this, OfertasGames.class);
                        startActivity(intentOfertas);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_novedades:
                        item.setChecked(true);
                        Intent intentNovedades = new Intent(ReclamacionPedido.this, NovedadesGames.class);
                        startActivity(intentNovedades);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_contacto:
                        item.setChecked(true);
                        Intent intentContacto = new Intent(ReclamacionPedido.this, ContactoSelect.class);
                        startActivity(intentContacto);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_donde_estamos:
                        item.setChecked(true);
                        Intent intentGPS = new Intent(ReclamacionPedido.this, MapsActivity.class);
                        startActivity(intentGPS);
                        drawerLayout.closeDrawers();
                        return true;
                }
                return false;
            }
        });


        imageView = findViewById(R.id.imagen_reclamacion_pedido);
        MaterialButton botonAddImagen =  findViewById(R.id.añadir_imagen);

        botonAddImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                Intent intent = new Intent();
                intent.setType("image/");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Seleccion una imagen"), GALLERY_REQUEST);

            }
        });


        final EditText edit_nombre = (EditText)findViewById(R.id.reclamacion_nombre);
        final EditText edit_subject = (EditText)findViewById(R.id.reclamacion_email);
        final EditText edit_consulta = (EditText)findViewById(R.id.consulta_consulta);
        final EditText edit_factura = (EditText)findViewById(R.id.reclamacion_id_factura);

        MaterialButton botonEnviarReclamacion =  findViewById(R.id.enviar_reclamacion);
        botonEnviarReclamacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                String to = "mailPractica2Studio@gmail.com";
                String subject = edit_subject.getText().toString() + " from "+ edit_nombre.getText().toString()
                        + " factura " + edit_factura.getText().toString();
                String message = edit_consulta.getText().toString();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[] { to });
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);
                email.putExtra(Intent.EXTRA_STREAM, uriImage);
                email.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                // need this to prompts email client only
                email.setType("message/rfc822");

                try {
                    startActivity(Intent.createChooser(email, "Choose an Email client :"));
                } catch (Exception e) {
                    e.printStackTrace();
                }



                Intent intentCarrito = new Intent(ReclamacionPedido.this, MainActivity.class);
                startActivityForResult(Intent.createChooser(intentCarrito, "Seleccion una imagen"), RETURN_MAIN);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item_shopping_cart = menu.findItem(R.id.nav_boton_tienda);
        item_shopping_cart.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intentCarrito = new Intent(ReclamacionPedido.this, Carrito.class);
                startActivity(intentCarrito);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data != null){
            this.uriImage = data.getData();
            imageView.setImageURI(this.uriImage);
        }

        if(requestCode == RETURN_MAIN){
            Intent intentCarrito = new Intent(ReclamacionPedido.this, MainActivity.class);
            startActivity(intentCarrito);
        }
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
