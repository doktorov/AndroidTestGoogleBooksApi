package ru.doktorov.test2.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.doktorov.test2.model.gson.GsonBooks;

public interface GoogleApi {
    //https://www.googleapis.com/books/v1/volumes?q=java&startIndex=10
    @GET("volumes")
    Call<GsonBooks> getBooks(@Query("q") String q);

    @GET("volumes")
    Call<GsonBooks> getBooksNext(@Query("q") String q, @Query("startIndex") int startIndex);
}