package com.example.resslen.projektbdio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class EditExamRequest extends StringRequest {
    private static final String EDIT_EXAM_REQUEST_URL = "https://bdiol4.herokuapp.com/exams/api/editExam";
    private Map<String, String> params;

    public EditExamRequest(String nowanazwa, String id_egzaminu, Response.Listener<String> listener) {
        super(Method.POST, EDIT_EXAM_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("new_name", nowanazwa);
        params.put("exam_id", id_egzaminu);


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}