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

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etLogin = (EditText) findViewById(R.id.etLogin);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final Button registerLink = (Button) findViewById(R.id.bRegisterHere);
        final Button bForgotPassword = (Button) findViewById(R.id.bPasswordForget);


        registerLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String email = etLogin.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                String state = jsonResponse.getString("status");
                                String surname = jsonResponse.getString("nazwisko");
                                String name = jsonResponse.getString("imie");
                                String id_studenta_or_egzaminatora = jsonResponse.getString("id_studenta_or_egzamintora");


                                int y = Integer.parseInt(state);
                                if(y == 1 ){
                                    Intent intent = new Intent(LoginActivity.this, UserPanelActivity.class);
                                    intent.putExtra("nazwisko", surname);
                                    intent.putExtra("imie", name);
                                    intent.putExtra("haslo", password);
                                    intent.putExtra("email", email);
                                    intent.putExtra("status", state);
                                    LoginActivity.this.startActivity(intent);
                                }else if(y == 0){
                                    Intent intent = new Intent(LoginActivity.this, ExaminerPanelActivity.class);
                                    intent.putExtra("nazwisko", surname);
                                    intent.putExtra("imie", name);
                                    intent.putExtra("haslo", password);
                                    intent.putExtra("email", email);
                                    intent.putExtra("status", state);
                                    LoginActivity.this.startActivity(intent);
                                }
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Logowanie niepomyślne")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(password, email, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

        bForgotPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent forgotIntent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                LoginActivity.this.startActivity(forgotIntent);
            }
        });

    }
}
