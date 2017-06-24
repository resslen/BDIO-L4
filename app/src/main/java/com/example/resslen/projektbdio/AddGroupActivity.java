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
                            boolean success = jsonResponse.getBoolean("success");
                            String error = jsonResponse.getString("error");
                            if(success){
                                Intent intent = new Intent(AddGroupActivity.this, ExaminerPanelActivity.class);
                                AddGroupActivity.this.startActivity(intent);
                            }
                            else if(error=="1"){
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddGroupActivity.this);
                                builder.setMessage("Nazwa i hasło nie mogą być puste!")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                            else if(error=="2"){
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddGroupActivity.this);
                                builder.setMessage("Takie hasło już istneieje w systemie!")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                            else if(error=="3"){
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
