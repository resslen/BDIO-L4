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
    String ID;
     String surname ;
     String name ;
     String password;
     String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_panel);

        final TextView tvSurname = (TextView) findViewById(R.id.tvSurname);
        final TextView tvName = (TextView) findViewById(R.id.tvName);
        final TextView tvEmail = (TextView) findViewById(R.id.tvEmail);
        final TextView tvPassword = (TextView) findViewById(R.id.tvPassword);
        final Button bChange = (Button) findViewById(R.id.bChange);


        //pobieranie danych z bazy
        Intent intent = getIntent();
        surname = intent.getStringExtra("nazwisko");
        name = intent.getStringExtra("imie");
        password = intent.getStringExtra("haslo");
        email = intent.getStringExtra("email");
        ID = intent.getStringExtra("ID");
        tvSurname.setText(surname);
        tvName.setText(name);
        tvPassword.setText(password);
        tvEmail.setText(email);

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

    /** Called when the user taps the EXAM button */
    public void clickExamButton(View view) {
        Intent intent = new Intent(this, ExamActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the STATISTICS button */
    public void clickStatisticsButton(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }
    /** Called when the user taps the STATISTICS button */
    public void onClickDolaczButton(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        intent.putExtra("ID", ID);
        intent.putExtra("nazwisko",surname);
        intent.putExtra("imie",name);
        intent.putExtra("haslo",password);
        intent.putExtra("email",email);
        startActivity(intent);
    }
}
