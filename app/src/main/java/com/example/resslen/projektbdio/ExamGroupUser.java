package com.example.resslen.projektbdio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ExamGroupUser extends AppCompatActivity {
    String ID;
    String surname;
    String name;
    String password;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_group_user);
        Intent intent = getIntent();
        surname = intent.getStringExtra("nazwisko");
        name = intent.getStringExtra("imie");
        password = intent.getStringExtra("haslo");
        email = intent.getStringExtra("email");
        ID = intent.getStringExtra("ID");
    }

    public void onClickDolaczButton(View view) {
        Intent intent = new Intent(this, DolaczDoGrupy.class);
        intent.putExtra("ID", ID);
        intent.putExtra("nazwisko", surname);
        intent.putExtra("imie", name);
        intent.putExtra("haslo", password);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    public void dolacz(View view) {
        Intent intent = new Intent(this, ExamActivity.class);
        startActivity(intent);
    }
}