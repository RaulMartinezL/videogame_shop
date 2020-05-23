package com.example.tienda_videojuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

public class ConfirmarPedido extends AppCompatActivity {

    public EditText mEmail;
    public String mSubject = "Confirmación pedido";
    public String mMessage = "Gracias por comprar en Canada Games, en breve llegará su pedido.";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);


        mEmail = (EditText)findViewById(R.id.mailID);


        MaterialButton botonContacto =  findViewById(R.id.boton_enviar_mail);
        botonContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                sendMail();

                Intent intentConfirmarPedido = new Intent(ConfirmarPedido.this, MainActivity.class);
                startActivity(intentConfirmarPedido);
            }
        });

    }




    private void sendMail(){

        String mail = mEmail.getText().toString().trim();

        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mail, mSubject, mMessage);

        javaMailAPI.execute();

    }


}
