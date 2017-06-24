package com.example.resslen.projektbdio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ShowGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showgroup);

        final TableLayout tabela = (TableLayout) findViewById(R.id.Tab);
        final TableRow R1= (TableRow) findViewById(R.id.R1);
        final TextView W1= (TextView) findViewById(R.id.W1);
        for(int i=0;i<=10;i++)
        W1.setText(W1.getText()+"1\n");
    }
}
