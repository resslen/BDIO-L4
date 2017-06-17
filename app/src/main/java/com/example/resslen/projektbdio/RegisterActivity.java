package com.example.resslen.projektbdio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Button bStudent = (Button) findViewById(R.id.bStudent);
        final Button bExaminer = (Button) findViewById(R.id.bExaminer);


        bStudent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent registerStudentIntent = new Intent(RegisterActivity.this, RegisterUserActivity.class);
                RegisterActivity.this.startActivity(registerStudentIntent);
            }
        });

        bExaminer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent registerExaminerIntent = new Intent(RegisterActivity.this, RegisterExaminerActivity.class);
                RegisterActivity.this.startActivity(registerExaminerIntent);
            }
        });
    }
}
