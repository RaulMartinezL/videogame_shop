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
    public EditText mSubject;
    public EditText mMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);


        String nombreJuego = getIntent().getStringExtra( "GameTitles");


        mEmail = (EditText)findViewById(R.id.mailID);
        mSubject =  (EditText)findViewById(R.id.subjectID);
        mMessage =  (EditText)findViewById(R.id.messageID);


        MaterialButton botonContacto =  findViewById(R.id.boton_enviar_mail);
        botonContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                sendMail();
            }
        });

    }


    private void sendMail(){

        String mail = mEmail.getText().toString().trim();
        String message = mMessage.getText().toString().trim();
        String subject = mSubject.getText().toString().trim();

        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mail, subject, message);

        javaMailAPI.execute();

    }


}
