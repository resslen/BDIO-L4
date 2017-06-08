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

import static com.example.resslen.projektbdio.R.id.bLogin;
import static com.example.resslen.projektbdio.R.id.etLogin;

public class ProfileChangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_change);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etSurname = (EditText) findViewById(R.id.etSurname);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final Button bEdit = (Button) findViewById(R.id.bEdit);

        bEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String surname = etSurname.getText().toString();
                final String name = etName.getText().toString();
                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Intent intent = new Intent(ProfileChangeActivity.this, UserPanelActivity.class);
                                intent.putExtra("nazwisko", surname);
                                intent.putExtra("imie", name);
                                intent.putExtra("email", email);
                                intent.putExtra("haslo", password);
                                ProfileChangeActivity.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileChangeActivity.this);
                                builder.setMessage("Zmiana danych niepomyślna")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };

                EditRequest editRequest = new EditRequest(surname, name, password, email, responseListener);
                RequestQueue queue = Volley.newRequestQueue(ProfileChangeActivity.this);
                queue.add(editRequest);
            }
        });
    }
}