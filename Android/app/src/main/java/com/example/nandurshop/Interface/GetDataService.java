package com.example.nandurshop.Interface;

import com.example.nandurshop.Model.GetCommodity;
import com.example.nandurshop.Model.PostPutDelCommodity;
import com.example.nandurshop.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface GetDataService {

    @GET("/api/commodity")
    Call<GetCommodity> getCommodity();

    @FormUrlEncoded
    @POST("/api/commodity")
    Call<PostPutDelCommodity> createCommodity(@Field("name") String nama,
                                              @Field("variety_id") Integer variety_id,
                                              @Field("planted_at") String planted_at,
                                              @Field("image_url") String image_url);
    @FormUrlEncoded
    @POST("/api/commodity")
    Call<PostPutDelCommodity> updateCommodity(@Field("id") Integer id,
                                              @Field("name") String nama,
                                              @Field("variety_id") Integer variety_id,
                                              @Field("planted_at") String planted_at,
                                              @Field("image_url") String image_url,
                                              @Field("_method") String method);

//    @GET("/api/commodity/{id}")
//    Call<Commodity> findCommodity();

    @FormUrlEncoded
    @POST("/api/commodity")
    Call<PostPutDelCommodity> deleteCommodity(@Field("id") Integer id,
                                              @Field("_method") String method);

    @FormUrlEncoded
    @POST("/api/auth/login")
    Call<User> login(@Field("email") String email, @Field("password") String password);
}