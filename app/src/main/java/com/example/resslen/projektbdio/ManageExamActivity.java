package com.example.resslen.projektbdio;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class ManageExamActivity extends AppCompatActivity {
    String ExamID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_exam);
        Intent Kintent = getIntent();
        final String ID = Kintent.getStringExtra("ID");
        final String id_egzaminu =Kintent.getStringExtra("id_egzaminu");
        final TextView nazwaEgzaminu=(TextView)findViewById(R.id.Nazwa_egzaminu_view) ;
        final TextView kodEgzaminu=(TextView)findViewById(R.id.Kod_Aktywacji_view);
        final TextView dataUtworzenia=(TextView)findViewById(R.id.Data_Utworzenia_view);
        final TextView Odatamodyfikacji=(TextView)findViewById(R.id.O_zmiana_kodu_view);
        final Button edytujKlucz = (Button) findViewById(R.id.bEdytujKlucz);
        final Button usunEgzamin = (Button) findViewById(R.id.bDeleteExam);
        final Button edytujEgzamin = (Button) findViewById(R.id.bEditExam);
        final TextView Testy=(TextView) findViewById(R.id.textView20);
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                boolean exists = false;
                try {
                   JSONObject jsonResponse = new JSONObject(response);
                   // JSONArray Exams = jsonResponse.getJSONArray("exam");

                      //  JSONObject egzamin= Exams.getJSONObject(0);
                        //String nazwa_egzaminu= egzamin.getString("nazwa");
                        //String  kod=egzamin.getString("kod_aktywacyjny");


                      //  nazwaEgzaminu.setText(nazwaEgzaminu.getText()+ "\n"+ nazwa_egzaminu);
                      //  kodEgzaminu.setText(kodEgzaminu.getText()+"\n"+kod);
                    nazwaEgzaminu.setText(jsonResponse.getString("exam"));



                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };
        ExamDetailsRequest ExamDetailsrequest = new ExamDetailsRequest(id_egzaminu,responseListener);
        RequestQueue queue = Volley.newRequestQueue(ManageExamActivity.this);
        queue.add(ExamDetailsrequest);

        usunEgzamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String error = jsonResponse.getString("error");

                            if (error.equals("0")) {
                                Intent intent = new Intent(ManageExamActivity.this, ManageExamActivity.class);
                                intent.putExtra("id_egzaminu", id_egzaminu);
                                startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ManageExamActivity.this);
                                builder.setMessage("Błąd!")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                };//
                DeleteExamRequest DeleteExamRequest = new DeleteExamRequest(id_egzaminu,responseListener);
                RequestQueue queue = Volley.newRequestQueue(ManageExamActivity.this);
                queue.add(DeleteExamRequest);

            }
        });


    }
}
