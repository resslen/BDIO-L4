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

import org.json.JSONException;
import org.json.JSONObject;

public class EditExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exam);

        Intent Kintent = getIntent();
        final String ID = Kintent.getStringExtra("ID");
        final String id_egzaminu =Kintent.getStringExtra("id_egzaminu");
        final TextView nowanazwa=(TextView)findViewById(R.id.etnowanazwa) ;
        final Button potwierdz= (Button)findViewById(R.id.bnowanazwapotwierdz);

        potwierdz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            String error = jsonResponse.getString("error");

                            if(error.equals("0")) {
                                Intent intent = new Intent(EditExamActivity.this, ManageExamActivity.class);
                                intent.putExtra("ID", ID);
                                intent.putExtra("id_egzaminu",nowanazwa.getText().toString());
                                startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(EditExamActivity.this);
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

                EditExamRequest editExamRequest = new EditExamRequest(id_egzaminu, nowanazwa.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(EditExamActivity.this);
                queue.add(editExamRequest);


            }
        });
    }
    }
}
