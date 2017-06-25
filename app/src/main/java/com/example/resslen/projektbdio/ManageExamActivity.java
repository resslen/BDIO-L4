package com.example.resslen.projektbdio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ManageExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_exam);
        Intent Kintent = getIntent();
        final String ID = Kintent.getStringExtra("ID");

    }

    public void clickEditExam(View view) {
        Intent intent = new Intent(this, EditKeyActivity.class);
        startActivity(intent);
    }

    public void clickEditKey(View view) {
        Intent intent = new Intent(this, EditKeyActivity.class);
        startActivity(intent);
    }

}