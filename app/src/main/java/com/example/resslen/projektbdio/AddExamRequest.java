package com.example.resslen.projektbdio;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kiler on 24.06.2017.
 */

public class AddExamRequest extends StringRequest {
    private static final String ADD_EXAM_REQUEST_URL = "https://bdiol4.herokuapp.com/groups/api/addexam";
    private Map<String, String> params;

    public AddExamRequest(String nazwa,String nazwa_grupy, String id_egzamintora, Response.Listener<String> listener) {
        super(Method.POST, ADD_EXAM_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("nazwa", nazwa);
        params.put("id_egzamintora", id_egzamintora);
        params.put("nazwa_grupy", nazwa_grupy);



    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
