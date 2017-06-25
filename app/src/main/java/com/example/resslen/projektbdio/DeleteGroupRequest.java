package com.example.resslen.projektbdio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeleteGroupRequest extends StringRequest {
    private static final String DELETE_GROUP_REQUEST_URL = "https://bdiol4.herokuapp.com/groups/api/deleteGroup";
    private Map<String, String> params;

    public DeleteGroupRequest(String id_grupy, Response.Listener<String> listener){
        super(Request.Method.POST, DELETE_GROUP_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_grupy", id_grupy);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}