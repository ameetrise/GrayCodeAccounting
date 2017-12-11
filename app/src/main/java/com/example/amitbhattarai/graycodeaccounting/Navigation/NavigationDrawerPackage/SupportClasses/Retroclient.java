package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ameet Bhattarai on 7/21/2017.
 */

public class Retroclient {
    private static final String ROOT_URL = "http://124.41.193.135:85/api/v1/";

    private static Retrofit getRetrofitInstance() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}

//124.41.193.135:85

//    public void getJson() {
//        ApiService api = Retroclient.getApiService();
//        Call<String> call = api.getNoticeJson("1", "20");
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response.isSuccessful()) {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//            }
//        });
//    }
//}
