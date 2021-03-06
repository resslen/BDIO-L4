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
    String WTesty[];
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
        final Button bedytujKlucz = (Button) findViewById(R.id.bEdytujKlucz);
        final Button usunEgzamin = (Button) findViewById(R.id.bDeleteExam);
        final Button bedytujEgzamin = (Button) findViewById(R.id.bEditExam);
        final TextView Testy=(TextView) findViewById(R.id.textView20);
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                   JSONObject jsonResponse = new JSONObject(response);

                    JSONObject Exam=jsonResponse.getJSONObject("exam");
                    JSONObject data=Exam.getJSONObject("ostatnia_zmiana_kodu");
                    JSONObject udata=Exam.getJSONObject("data_utworzenia");
                        String nazwa_egzaminu= Exam.getString("nazwa");
                    String  kod=Exam.getString("kod_aktywacyjny");
                    String date=data.getString("date");
                    String hours=data.getString("hours");
                    String month=data.getString("month");
                    String year=data.getString("year");
                    String minutes=data.getString("minutes");
                    String seconds=data.getString("seconds");
                    String day=data.getString("day");

                    String udate=udata.getString("date");
                    String uhours=udata.getString("hours");
                    String umonth=udata.getString("month");
                    String uyear=udata.getString("year");
                    String uminutes=udata.getString("minutes");
                    String useconds=udata.getString("seconds");
                    String uday=udata.getString("day");

                        Odatamodyfikacji.setText((Odatamodyfikacji.getText()+"\n"+year+"-"+month+"-"+day+"-"+hours+":"+minutes+":"+seconds));
                      nazwaEgzaminu.setText(nazwaEgzaminu.getText()+ "\n"+ nazwa_egzaminu);
                        kodEgzaminu.setText(kodEgzaminu.getText()+"\n"+kod);
                    dataUtworzenia.setText(dataUtworzenia.getText()+"\n"+uyear+"-"+umonth+"-"+uday+"-"+uhours+":"+uminutes+":"+useconds);
                    //(͡° ͜ʖ ͡°)
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };
        ExamDetailsRequest ExamDetailsrequest = new ExamDetailsRequest(id_egzaminu,responseListener);
        RequestQueue queue = Volley.newRequestQueue(ManageExamActivity.this);
        queue.add(ExamDetailsrequest);
        Response.Listener<String> responseListener2 = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray tests = jsonResponse.getJSONArray("tests");

                    String ID_n[] = new String[tests.length()];
                    String Nazwa[] = new String[tests.length()];
                    for (int i = 0; i < tests.length(); i++) {
                        JSONObject obiekt = tests.getJSONObject(i);
                        String id = obiekt.getString("id_testu");
                        ID_n[i] = id;
                    }
                    for (int i = 0; i < tests.length(); i++) {
                        JSONObject obiekt = tests.getJSONObject(i);
                        String name = obiekt.getString("nazwa");
                        Nazwa[i] = name;
                    }
                    WTesty= new String[tests.length()];
                    Testy.setText("");
                    for(int i=0; i<tests.length();i++) {

                        Testy.setText(Testy.getText() + ID_n[i] + "." + Nazwa[i] + "\n");
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };
        TestRequest   TestRequest = new   TestRequest(id_egzaminu,responseListener2);
        RequestQueue queue2 = Volley.newRequestQueue(ManageExamActivity.this);
        queue2.add(  TestRequest);

        usunEgzamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener3 = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String error = jsonResponse.getString("error");

                            if (error.equals("0")) {
                                Intent intent = new Intent(ManageExamActivity.this, ExamActivity.class);
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
                DeleteExamRequest DeleteExamRequest = new DeleteExamRequest(id_egzaminu,responseListener3);
                RequestQueue queue = Volley.newRequestQueue(ManageExamActivity.this);
                queue.add(DeleteExamRequest);

            }
        });


        bedytujEgzamin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent(ManageExamActivity.this, EditExamActivity.class);
                intent.putExtra("ID", ID);
                intent.putExtra("id_egzaminu",id_egzaminu);
                startActivity(intent);


            }
        });
        bedytujKlucz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent(ManageExamActivity.this, EditKeyActivity.class);
                intent.putExtra("ID", ID);
                intent.putExtra("id_egzaminu",id_egzaminu);
                startActivity(intent);


            }
        });
    }


}
