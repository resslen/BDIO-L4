package com.example.resslen.projektbdio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.android.volley.Response;

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

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int i;
                boolean exists = false;
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray Exams = jsonResponse.getJSONArray("exams");

                    for (i = 0; i < Exams.length(); i++) {
                        JSONObject obiekt = Exams.getJSONObject(i);
                        String id_egz = obiekt.getString("id_egzaminu");
                        if(id_egzaminu.equals(id_egz)) {
                            exists = true;
                            break;
                        }
                    }
                    if(exists) {
                        JSONObject egzamin= Exams.getJSONObject(i);
                        String nazwa_egzaminu= egzamin.getString("nazwa");
                        String  kod=egzamin.getString("kod_aktywacyjny");
                        //nwm jak data
                        nazwaEgzaminu.setText(nazwaEgzaminu.getText()+ "\n"+ nazwa_egzaminu);
                        kodEgzaminu.setText(kodEgzaminu.getText()+"\n"+kod);



                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };

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