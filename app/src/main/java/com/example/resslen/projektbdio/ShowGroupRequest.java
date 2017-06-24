package com.example.resslen.projektbdio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ShowGroupRequest extends StringRequest {
    private static final String SHOW_GROUP_REQUEST_URL = "https://bdiol4.herokuapp.com/groups/api/showGroups";
    private Map<String, String> params;

    public ShowGroupRequest(String id_egzamintora, Response.Listener<String> listener){
        super(Request.Method.POST, SHOW_GROUP_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_egzamintora", id_egzamintora);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
