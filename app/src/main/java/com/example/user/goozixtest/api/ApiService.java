package com.example.user.goozixtest.api;

import com.example.user.goozixtest.model.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    /*
       Retrifit get annotation with our URL
       And our method that will return us the List of MovieList
        */
    @GET("users?since=13")
    Call<List<Example>> getMyJSON();  //метод для получения списков всех контактов

    @GET("users/{login}")
    Call<Example> getUser(@Path("login") String login);  //метод для получения одного контакта

}
/* add pagination */