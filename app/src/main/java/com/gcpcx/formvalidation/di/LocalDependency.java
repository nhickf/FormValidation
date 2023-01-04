package com.gcpcx.formvalidation.di;

import com.gcpcx.formvalidation.data.repository.INetworkRepository;
import com.gcpcx.formvalidation.data.repository.NetworkRepositoryImpl;
import com.gcpcx.formvalidation.data.service.client.RetrofitService;

public class LocalDependency {

    public static INetworkRepository getNetworkRepository(INetworkRepository.INetworkCallback networkCallback){
        return new NetworkRepositoryImpl(RetrofitService.retrofitService(),networkCallback);
    }

}
