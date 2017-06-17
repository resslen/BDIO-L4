package com.example.resslen.projektbdio;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Resslen on 02.06.2017.
 */

public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "https://bdiol4.herokuapp.com/groups/logowanie";
    private Map<String, String> params;

    public LoginRequest(String password, String email, Response.Listener<String> listener){
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("haslo", password);
        params.put("email", email);

    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
