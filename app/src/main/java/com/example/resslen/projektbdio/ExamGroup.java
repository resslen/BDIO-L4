package com.example.resslen.projektbdio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExamGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_group);

    }

    public void clickExamButton(View view) {
        Intent Kintent = getIntent();
        final String ID = Kintent.getStringExtra("ID");
        Intent intent = new Intent(this, ExamActivity.class);
        intent.putExtra("ID", ID);
        startActivity(intent);
    }

    public void clickGrupyButton(View view) {
        Intent intent2 = getIntent();
        final String ID = intent2.getStringExtra("ID");
        Intent intent = new Intent(this, ShowGroupActivity.class);
        intent.putExtra("ID", ID);
        startActivity(intent);
    }
}