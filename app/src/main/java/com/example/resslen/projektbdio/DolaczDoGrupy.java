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

public class DolaczDoGrupy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dolacz_do_grupy);

        Intent intent = getIntent();
        final String ID = intent.getStringExtra("ID");
        final String nazwisko=intent.getStringExtra("nazwisko");
        final String imie=intent.getStringExtra("imie");
        final String haslo=intent.getStringExtra("haslo");
        final String email=intent.getStringExtra("email");

        final Button OK = (Button)findViewById(R.id.OK);
        final EditText eHaslo=(EditText)findViewById(R.id.eHaslo);
        OK.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            String error = jsonResponse.getString("error");

                            if(error.equals("0")) {
                                Intent intent = new Intent(DolaczDoGrupy.this, UserPanelActivity.class);
                                intent.putExtra("ID", ID);
                                intent.putExtra("nazwisko",nazwisko);
                                intent.putExtra("imie",imie);
                                intent.putExtra("haslo",haslo);
                                intent.putExtra("email",email);
                                startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(DolaczDoGrupy.this);
                                builder.setMessage("Błąd!")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e){
                            e.printStackTrace();
                        }


                    }

                };

                DolaczDoGrupyRequest dolaczDoGrupyRequest = new DolaczDoGrupyRequest(ID,eHaslo.getText().toString(), responseListener);
                RequestQueue queue = Volley.newRequestQueue(DolaczDoGrupy.this);
                queue.add(dolaczDoGrupyRequest);


            }
        });
    }
}
