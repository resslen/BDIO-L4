package com.example.resslen.projektbdio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.password;

public class UserPanelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_panel);

        final TextView tvSurname = (TextView) findViewById(R.id.tvSurname);
        final TextView tvName = (TextView) findViewById(R.id.tvName);
        final TextView tvEmail = (TextView) findViewById(R.id.tvEmail);
        final TextView tvPassword = (TextView) findViewById(R.id.tvPassword);
        final Button bChange = (Button) findViewById(R.id.bChange);
        final TextView dupa = (TextView) findViewById(R.id.tvDUPA);


        //pobieranie danych z bazy
        Intent intent = getIntent();
        final String surname = intent.getStringExtra("nazwisko");
        final String name = intent.getStringExtra("imie");
        final String password = intent.getStringExtra("haslo");
        final String email = intent.getStringExtra("email");
        final String dupa2 = intent.getStringExtra("status");

        tvSurname.setText(surname);
        tvName.setText(name);
        tvPassword.setText(password);
        tvEmail.setText(email);
        dupa.setText(dupa2);

        bChange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent changeIntent = new Intent(UserPanelActivity.this, ProfileChangeActivity.class);
                changeIntent.putExtra("1", surname);
                changeIntent.putExtra("2", name);
                changeIntent.putExtra("3", email);
                changeIntent.putExtra("4", password);
                UserPanelActivity.this.startActivity(changeIntent);
            }
        }); 

    }
}
