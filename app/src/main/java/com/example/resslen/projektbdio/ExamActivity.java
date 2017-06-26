package com.example.resslen.projektbdio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExamActivity extends AppCompatActivity {
    String egzaminy[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        Intent Kintent= getIntent();
        final String ID = Kintent.getStringExtra("ID");
        final TextView teksteg = (TextView)findViewById(R.id.textView7);
        final Button bWyswietleg =(Button) findViewById(R.id.bShowExam3);
        final Button Dodajeg= (Button) findViewById(R.id.bCreateExam);
        final EditText ideg =(EditText) findViewById(R.id.etPodajidegzaminu);
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray exams = jsonResponse.getJSONArray("exams");

                    String ID_n[] = new String[exams.length()];
                    String Nazwa[] = new String[exams.length()];
                    for (int i = 0; i < exams.length(); i++) {
                        JSONObject obiekt = exams.getJSONObject(i);
                        String id = obiekt.getString("id_egzaminu");
                        ID_n[i] = id;
                    }
                    for (int i = 0; i < exams.length(); i++) {
                        JSONObject obiekt = exams.getJSONObject(i);
                        String name = obiekt.getString("nazwa");
                        Nazwa[i] = name;
                    }
                   egzaminy= new String[exams.length()];
                    teksteg.setText("");
                    for(int i=0; i<exams.length();i++) {

                        teksteg.setText(teksteg.getText() + ID_n[i] + "." + Nazwa[i] + "\n");
                    }

                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
            };
        ShowExamRequest showExamRequest = new ShowExamRequest(ID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ExamActivity.this);
        queue.add(showExamRequest);

        bWyswietleg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String id_eg=ideg.getText().toString();
                Intent intent = new Intent(ExamActivity.this, ManageExamActivity.class);
                intent.putExtra("ID", ID);
                intent.putExtra("id_egzaminu",id_eg);
                startActivity(intent);

            }
        });
        Dodajeg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ExamActivity.this, AddExamActivity.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });

    }




}

