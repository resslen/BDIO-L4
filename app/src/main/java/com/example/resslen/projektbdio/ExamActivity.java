package com.example.resslen.projektbdio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
    }


    public void clickManagetExamButton(View view) {
        Intent Kintent = getIntent();
        final String ID = Kintent.getStringExtra("ID");
        Intent intent = new Intent(this, ManageExamActivity.class);
        intent.putExtra("ID", ID);
        startActivity(intent);
    }

    public void clickCreateButton(View view) {
        Intent Kintent = getIntent();
        final String ID = Kintent.getStringExtra("ID");
        Intent intent = new Intent(this, AddExamActivity.class);
        intent.putExtra("ID", ID);
        startActivity(intent);
    }

    public void clickShowExamButton(View view) {
        Intent intent = new Intent(this, ShowExamActivity.class);
        startActivity(intent);
    }
}
