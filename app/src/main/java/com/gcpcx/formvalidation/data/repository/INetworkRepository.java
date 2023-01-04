package com.gcpcx.formvalidation.data.repository;

import com.gcpcx.formvalidation.data.service.reponse.FormResponse;

public interface INetworkRepository {

    void getFormResponse();

    interface INetworkCallback{
        void onSuccessResponse(FormResponse formResponse);
        void onFailedResponse(FormResponse formResponse);
    }
}
