package com.example.resslen.projektbdio;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        Intent intent2 = getIntent();

        final String ID = intent2.getStringExtra("ID");
        final EditText nazwa = (EditText) findViewById(R.id.editText2);
        final EditText haslo = (EditText) findViewById(R.id.editText3);
        final Button DODAJ = (Button) findViewById(R.id.button5);
       // final TextView tekst = (TextView) findViewById(R.id.textView7);

        DODAJ.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String nazwa_g = nazwa.getText().toString();
                final String haslo_g = haslo.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            String error = jsonResponse.getString("error");

                            if(error.equals("0")){
                                Intent intent = new Intent(AddGroupActivity.this, ExaminerPanelActivity.class);
                                startActivity(intent);
                            }
                            else if(error.equals("1")){

                                AlertDialog.Builder builder = new AlertDialog.Builder(AddGroupActivity.this);
                                builder.setMessage("Nazwa i hasło nie mogą być puste!")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                            else if(error.equals("2")){
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddGroupActivity.this);
                                builder.setMessage("Takie hasło już istneieje w systemie!")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                            else if(error.equals("3")){
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddGroupActivity.this);
                                builder.setMessage("Błąd w przetwarzaniu bazy danych")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }


                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };

                AddGroupRequest addgrouprequest = new AddGroupRequest(ID, nazwa_g, haslo_g, responseListener);
                RequestQueue queue = Volley.newRequestQueue(AddGroupActivity.this);
                queue.add(addgrouprequest);
            }
        });
    }

}
