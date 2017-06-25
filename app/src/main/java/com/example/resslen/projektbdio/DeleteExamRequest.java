package com.example.resslen.projektbdio;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kiler on 25.06.2017.
 */

public class DeleteExamRequest extends StringRequest {
    private static final String DELETE_EXAM_REQUEST_URL = "https://bdiol4.herokuapp.com/exams/api/deleteExam";
    private Map<String, String> params;

    public DeleteExamRequest(String id_egzaminu, Response.Listener<String> listener){
        super(Request.Method.POST, DELETE_EXAM_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("exam_id", id_egzaminu);

    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}