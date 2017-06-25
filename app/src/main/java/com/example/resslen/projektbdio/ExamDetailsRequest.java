package com.example.resslen.projektbdio;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kiler on 25.06.2017.
 */

public class ExamDetailsRequest extends StringRequest {
    private static final String EXAM_DETAILS_REQUEST_URL = "https://bdiol4.herokuapp.com/exams/api/getExamDetails";
    private Map<String, String> params;

    public  ExamDetailsRequest(String id_egzaminu,Response.Listener<String> listener){
        super(Method.POST, EXAM_DETAILS_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_egzaminu", id_egzaminu);

    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
