package com.example.resslen.projektbdio;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShowGroupActivity extends AppCompatActivity {
    String grupy[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showgroup);
        Intent intent2= getIntent();
        final String ID = intent2.getStringExtra("ID");
        final TextView tekstg=(TextView) findViewById(R.id.textView9);
        final Button gDodaj= (Button) findViewById(R.id.bDodaj);
        final EditText gNazwa = (EditText)findViewById(R.id.gNazwa) ;
        final Button bWyswietl = (Button) findViewById(R.id.bWyswietl) ;
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray groups = jsonResponse.getJSONArray("groups");

                    String ID_n[] = new String[groups.length()];
                    String Nazwa[] = new String[groups.length()];
                    for (int i = 0; i < groups.length(); i++) {
                        JSONObject obiekt = groups.getJSONObject(i);
                        String id = obiekt.getString("id_grupy");
                        ID_n[i] = id;
                    }
                    for (int i = 0; i < groups.length(); i++) {
                        JSONObject obiekt = groups.getJSONObject(i);
                        String name = obiekt.getString("nazwa");
                        Nazwa[i] = name;
                    }
                    grupy= new String[groups.length()];
                    tekstg.setText("");
                    for(int i=0; i<groups.length();i++) {

                        tekstg.setText(tekstg.getText() + ID_n[i] + "." + Nazwa[i] + "\n");
                    }

                    } catch (JSONException e){
                        e.printStackTrace();
                    }


                }

        };

        ShowGroupRequest showGroupRequest = new ShowGroupRequest(ID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ShowGroupActivity.this);
        queue.add(showGroupRequest);

        gDodaj.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent2= getIntent();
                final String ID = intent2.getStringExtra("ID");
                Intent intent = new Intent(ShowGroupActivity.this, AddGroupActivity.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });
        bWyswietl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent2= getIntent();
                final String ID = intent2.getStringExtra("ID");
                final String g_Nazwa=gNazwa.getText().toString();
                Intent intent = new Intent(ShowGroupActivity.this, GroupPropertiesActivity.class);
                intent.putExtra("ID", ID);
                intent.putExtra("g_nazwa",g_Nazwa);
                startActivity(intent);
            }
        });



    }


}



