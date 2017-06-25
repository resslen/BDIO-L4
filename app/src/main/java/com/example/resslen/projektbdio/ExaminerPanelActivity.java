package com.example.resslen.projektbdio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ExaminerPanelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examiner_panel);

        final TextView tvSurname = (TextView) findViewById(R.id.tvSurname);
        final TextView tvName = (TextView) findViewById(R.id.tvName);
        final TextView tvEmail = (TextView) findViewById(R.id.tvEmail);
        final TextView tvPassword = (TextView) findViewById(R.id.tvPassword);
        final Button bChange = (Button) findViewById(R.id.bChange);
        final Button bLogOut = (Button) findViewById(R.id.bLogOut);


        //pobieranie danych z bazy
        Intent intent = getIntent();
        final String surname = intent.getStringExtra("nazwisko");
        final String name = intent.getStringExtra("imie");
        final String password = intent.getStringExtra("haslo");
        final String email = intent.getStringExtra("email");


        tvSurname.setText(surname);
        tvName.setText(name);
        tvPassword.setText(password);
        tvEmail.setText(email);

        bChange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent changeIntent = new Intent(ExaminerPanelActivity.this, ProfileChangeActivity.class);
                changeIntent.putExtra("1", surname);
                changeIntent.putExtra("2", name);
                changeIntent.putExtra("3", password);
                changeIntent.putExtra("4", email);
                ExaminerPanelActivity.this.startActivity(changeIntent);
            }
        });

        bLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeIntent = new Intent(ExaminerPanelActivity.this, LoginActivity.class);
                ExaminerPanelActivity.this.startActivity(changeIntent);
                finish();
            }
        });

    }

    /** Called when the user taps the EXAM button */

    /** Called when the user taps the STATISTICS button */
    public void clickStatisticsButton(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }



    public void clickZacznij(View view) {
        Intent intent2 = getIntent();
        final String ID = intent2.getStringExtra("ID");
        Intent intent = new Intent(this, ExamGroup.class);
        intent.putExtra("ID", ID);
        startActivity(intent);
    }
}