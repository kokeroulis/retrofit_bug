package com.example.kokeroulis.retrofit_bug;

import com.example.kokeroulis.retrofit_bug.model.Github;
import com.example.kokeroulis.retrofit_bug.model.Owner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;

public interface RetrofitServiceExample {

    @GET("/square/retrofit")
    void getRetrofit(Callback<Github> callback);

     public static class Implementation {
        public static RetrofitServiceExample get() {
            return getBuilder()
                    .build()
                    .create(RetrofitServiceExample.class);
        }

        static RestAdapter.Builder getBuilder() {
            return new RestAdapter.Builder()
                    .setConverter(new GsonConverter(getGson()))
                    .setClient(new OkClient(new OkHttpClient()))
                    .setEndpoint("https://api.github.com/repos");
        }

        private static Gson getGson() {
            return new GsonBuilder()
                    .registerTypeAdapter(Github.class, new Github())
                    .create();
        }
    }
}

