package com.gcpcx.formvalidation.data.repository;

import com.gcpcx.formvalidation.data.service.reponse.FormResponse;
import com.gcpcx.formvalidation.data.service.FormService;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkRepositoryImpl implements INetworkRepository {
    private final FormService formService;
    private final INetworkCallback networkCallback;

    public NetworkRepositoryImpl(FormService formService, INetworkCallback networkCallback
    ) {
        this.networkCallback = networkCallback;
        this.formService = formService;
    }

    @Override
    public void getFormResponse() {
        try {
            formService.getFormResponse().enqueue(new Callback<FormResponse>() {
                @Override
                public void onResponse(Call<FormResponse> call, Response<FormResponse> response) {
                    if (response.isSuccessful()) {
                        networkCallback.onSuccessResponse(response.body());
                    }
                }

                @Override
                public void onFailure(Call<FormResponse> call, Throwable t) {
                    networkCallback.onFailedResponse(new FormResponse(
                            new Exception(t),t.getMessage()
                    ));
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            networkCallback.onFailedResponse(new FormResponse(
                    e,e.getMessage()
            ));
        }
    }
}
