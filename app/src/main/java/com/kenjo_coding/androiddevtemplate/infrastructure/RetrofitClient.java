package com.kenjo_coding.androiddevtemplate.infrastructure;

import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://pokeapi.co/api/v2/";

    // logger
    HttpLoggingInterceptor logger = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC); // BASIC or BODY

    // OkHttpClientインスタンス
    OkHttpClient okhttpClient = new OkHttpClient.Builder()
            .connectTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90,TimeUnit.SECONDS)
            .addInterceptor(logger)
            .build();


    // Retrofitインスタンスの生成
    public ApiService createApiService() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okhttpClient)
                .build()
                .create(ApiService.class);
    }

}
