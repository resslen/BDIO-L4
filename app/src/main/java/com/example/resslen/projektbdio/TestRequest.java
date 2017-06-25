package com.example.resslen.projektbdio;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kiler on 25.06.2017.
 */

public class TestRequest extends StringRequest {
    private static final String TEST_REQUEST_URL = "https://bdiol4.herokuapp.com/exams/api/getAllTestsOnExam";
    private Map<String, String> params;

    public TestRequest(String id_egzamintora,Response.Listener<String> listener){
        super(Method.POST, TEST_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("exam_id", id_egzamintora);

    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}