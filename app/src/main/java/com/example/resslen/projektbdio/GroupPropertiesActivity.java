package com.example.resslen.projektbdio;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

public class GroupPropertiesActivity extends AppCompatActivity {
    String groupID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_properties);
        Intent intent2= getIntent();
        final String ID = intent2.getStringExtra("ID");
        final String Nazwa =intent2.getStringExtra("g_nazwa");
        final TextView czlonkowieLista=(TextView)findViewById(R.id.czlonkowieLista) ;
        final TextView nazwaGrupy=(TextView)findViewById(R.id.nazwa_view) ;
        final TextView hasloGrupy=(TextView)findViewById(R.id.haslo_view) ;
        final Button usunCzlonka=(Button)findViewById(R.id.bOK) ;
        final EditText eID=(EditText)findViewById(R.id.eID) ;
        final Button bEdytuj=(Button)findViewById(R.id.bEdytuj) ;
        final Button bUsun=(Button)findViewById(R.id.bUsun) ;
        final Button bPowrot=(Button)findViewById(R.id.bPowrot) ;
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                int i;
                boolean exists=false;
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray groups = jsonResponse.getJSONArray("groups");

                    for (i = 0; i < groups.length(); i++) {
                        JSONObject obiekt = groups.getJSONObject(i);
                        String name = obiekt.getString("nazwa");
                        if(Nazwa.equals(name))
                        {
                            exists=true;
                            break;
                        }
                    }
                    if(exists) {
                        JSONObject grupa = groups.getJSONObject(i);
                        JSONArray lista = grupa.getJSONArray("usersList");
                        String haslo = grupa.getString("haslo");
                        groupID=grupa.getString("id_grupy");
                        for (int j = 0; j < lista.length(); j++) {
                            JSONObject osoba = lista.getJSONObject(j);
                            String id_osoby = osoba.getString("id_uzytkownika");
                            String imie_osoby = osoba.getString("imie");
                            String nazwisko_osoby = osoba.getString("nazwisko");
                            czlonkowieLista.setText(czlonkowieLista.getText() + id_osoby + "." + imie_osoby + " " + nazwisko_osoby + "\n");
                        }

                        hasloGrupy.setText(hasloGrupy.getText() + "\n" + haslo);
                        nazwaGrupy.setText(nazwaGrupy.getText() + "\n" + Nazwa);
                    }
                    else
                        nazwaGrupy.setText(nazwaGrupy.getText() + "\n" + "Grupa nie istnieje!");

                } catch (JSONException e){
                    e.printStackTrace();
                }


            }

        };

        ShowGroupRequest showGroupRequest = new ShowGroupRequest(ID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(GroupPropertiesActivity.this);
        queue.add(showGroupRequest);

        usunCzlonka.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            String error = jsonResponse.getString("error");

                            if(error.equals("0")) {
                                Intent intent = new Intent(GroupPropertiesActivity.this, GroupPropertiesActivity.class);
                                intent.putExtra("ID", ID);
                                intent.putExtra("g_nazwa",Nazwa);
                                startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(GroupPropertiesActivity.this);
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

                UsunCzlonkaRequest usunCzlonkaRequest = new UsunCzlonkaRequest(groupID,eID.getText().toString(), responseListener);
                RequestQueue queue = Volley.newRequestQueue(GroupPropertiesActivity.this);
                queue.add(usunCzlonkaRequest);


            }
        });


        bEdytuj.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent(GroupPropertiesActivity.this, EditGroupActivity.class);
                intent.putExtra("ID", ID);
                intent.putExtra("groupID",groupID);
                startActivity(intent);


            }
        });
        bPowrot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent(GroupPropertiesActivity.this, ShowGroupActivity.class);
                intent.putExtra("ID", ID);
                startActivity(intent);


            }
        });

        bUsun.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            String error = jsonResponse.getString("error");

                            if(error.equals("0")) {
                                Intent intent = new Intent(GroupPropertiesActivity.this, ShowGroupActivity.class);
                                intent.putExtra("ID", ID);
                                startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(GroupPropertiesActivity.this);
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

                DeleteGroupRequest deleteGroupRequest = new DeleteGroupRequest(groupID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(GroupPropertiesActivity.this);
                queue.add(deleteGroupRequest);


            }
        });
    }
}
