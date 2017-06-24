package com.example.resslen.projektbdio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GroupsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
    }

    public void clickAddgroupButton(View view) {
        Intent intent2= getIntent();
        final String ID = intent2.getStringExtra("ID");
        Intent intent = new Intent(this, AddGroupActivity.class);
        intent.putExtra("ID", ID);
        startActivity(intent);
    }

    public void clickEditGroupButton(View view) {
        Intent intent = new Intent(this, EditGroupActivity.class);
        startActivity(intent);
    }

    public void clickShowGroupButton(View view) {
        Intent intent = new Intent(this, ShowGroupActivity.class);
        startActivity(intent);
    }
}
