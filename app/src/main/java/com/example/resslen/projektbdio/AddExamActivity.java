package com.example.resslen.projektbdio;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam);
        Intent Kintent = getIntent();
        final String ID = Kintent.getStringExtra("ID");
        final EditText NazwaEgazminu =(EditText) findViewById(R.id.etdNazwaEgazminu);

        final EditText nazwagrupy = (EditText) findViewById(R.id.etdPodajGrupe);
        final Button DodajEgzamin = (Button) findViewById(R.id.bdDodajEgzamin);



        DodajEgzamin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String nazwa_e = NazwaEgazminu.getText().toString();

                final String grupa = nazwagrupy.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            String error = jsonResponse.getString("error");
                            if(error.equals("0")){
                                Intent intent = new Intent(AddExamActivity.this, ExaminerPanelActivity.class);
                                startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddExamActivity.this);
                                builder.setMessage("Nie poprawne dodanie egzaminu")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                };

                AddExamRequest addexamrequest = new AddExamRequest(nazwa_e,grupa,ID,responseListener);
                RequestQueue queue = Volley.newRequestQueue(AddExamActivity.this);
                queue.add(addexamrequest);
            }

        });

    }
}