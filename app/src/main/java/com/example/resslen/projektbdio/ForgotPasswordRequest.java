package com.example.resslen.projektbdio;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Resslen on 07.06.2017.
 */

public class ForgotPasswordRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "https://resslen.000webhostapp.com/ForgotPassword.php";
    private Map<String, String> params;

    public ForgotPasswordRequest(String email, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
