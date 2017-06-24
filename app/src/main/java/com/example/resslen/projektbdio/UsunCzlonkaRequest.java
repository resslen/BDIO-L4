package com.example.resslen.projektbdio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UsunCzlonkaRequest extends StringRequest {
    private static final String USUN_CZLONKA_REQUEST_URL = "https://bdiol4.herokuapp.com/groups/api/deleteMember";
    private Map<String, String> params;

    public UsunCzlonkaRequest(String id_grupy,String id_uzytkownika, Response.Listener<String> listener){
        super(Request.Method.POST, USUN_CZLONKA_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_grupy", id_grupy);
        params.put("id_uzytkownika", id_uzytkownika);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
