package com.gcpcx.formvalidation.data.service;

import com.gcpcx.formvalidation.data.service.reponse.FormResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FormService {

    @GET("/v3/3a615d82-41a0-4dbf-8baf-983c28d46eca")
    Call<FormResponse> getFormResponse();

}
