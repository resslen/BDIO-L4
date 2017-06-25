package com.example.resslen.projektbdio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class EditGroupRequest extends StringRequest {
    private static final String EDIT_GROUP_REQUEST_URL = "https://bdiol4.herokuapp.com/groups/api/editGroup";
    private Map<String, String> params;

    public EditGroupRequest(String id_grupy,String nazwa_grupy,String haslo, Response.Listener<String> listener){
        super(Request.Method.POST, EDIT_GROUP_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_grupy", id_grupy);
        params.put("nazwa", nazwa_grupy);
        params.put("password", haslo);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
