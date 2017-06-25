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

public class EditGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_group);
        final EditText nowaNazwa=(EditText)findViewById(R.id.nowaNazwa) ;
        final EditText noweHaslo=(EditText)findViewById(R.id.noweHaslo) ;
        final Button OK = (Button)findViewById(R.id.OK) ;
        Intent intent2= getIntent();
        final String ID = intent2.getStringExtra("ID");
        final String groupID =intent2.getStringExtra("groupID");

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
                                Intent intent = new Intent(EditGroupActivity.this, GroupPropertiesActivity.class);
                                intent.putExtra("ID", ID);
                                intent.putExtra("g_nazwa",nowaNazwa.getText().toString());
                                startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(EditGroupActivity.this);
                                builder.setMessage("Sprawdź poprawność danych!")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e){
                            e.printStackTrace();
                        }


                    }

                };

                EditGroupRequest editGroupRequest = new EditGroupRequest(groupID, nowaNazwa.getText().toString(),
                        noweHaslo.getText().toString(), responseListener);
                RequestQueue queue = Volley.newRequestQueue(EditGroupActivity.this);
                queue.add(editGroupRequest);


            }
        });
    }
}
