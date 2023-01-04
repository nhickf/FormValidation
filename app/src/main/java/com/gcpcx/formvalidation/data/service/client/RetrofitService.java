package com.gcpcx.formvalidation.data.service.client;

import com.gcpcx.formvalidation.data.service.FormService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static Retrofit retrofit = null;

    public static FormService retrofitService(){
        OkHttpClient okHttpClient;
        HttpLoggingInterceptor loggingInterceptor;

        loggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .build();


        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://run.mocky.io/v3/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(FormService.class);
    }

}
