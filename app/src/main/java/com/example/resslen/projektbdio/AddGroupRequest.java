package com.example.resslen.projektbdio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by KArul:) dzisiaj
 */

public class AddGroupRequest extends StringRequest {
    private static final String ADD_GROUP_REQUEST_URL = "https://bdiol4.herokuapp.com/groups/api/addGroup";
    private Map<String, String> params;

    public AddGroupRequest(String id_egzamintora, String nazwa_grupy, String haslo_grupy, Response.Listener<String> listener){
        super(Method.POST, ADD_GROUP_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_egzamintora", id_egzamintora);
        params.put("nazwa_grupy", nazwa_grupy);
        params.put("haslo_grupy", haslo_grupy);

    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
