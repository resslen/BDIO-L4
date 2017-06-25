package com.example.resslen.projektbdio;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Resslen on 07.06.2017.
 */

public class EditRequestActivity extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "DODAJ API";
    private Map<String, String> params;

    public EditRequestActivity(String surname, String name, String password, String email, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("nazwisko", surname);
        params.put("imie", name);
        params.put("haslo", password);
        params.put("email", email);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
