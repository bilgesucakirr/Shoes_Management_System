package com.example.shoesmanagement;

import com.example.shoesmanagement.sampledata.DataPost;
import com.example.shoesmanagement.sampledata.DataPut;
import com.example.shoesmanagement.sampledata.dataModel;

import java.util.List;

import retrofit2.http.DELETE;
import retrofit2.http.GET;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.PUT;


public interface MyApiCall {

    // https://shoesapi.intalalab.net/

    @GET("api/Shoe")
    Call<List<dataModel>> getShoes();

    @PUT("api/Shoe/{id}")
    Call<DataPut> updateShoe(@retrofit2.http.Path("id") int id, @retrofit2.http.Body DataPut data);

    @POST("api/Shoe")
    Call<DataPost> addShoe(@retrofit2.http.Body DataPost data);

    @DELETE("api/Shoe/{id}")
    Call<Void> deleteShoe(@retrofit2.http.Path("id") int id);
}
