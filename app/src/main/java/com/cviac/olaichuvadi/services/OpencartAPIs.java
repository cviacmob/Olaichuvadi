package com.cviac.olaichuvadi.services;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by User on 12/26/2016.
 */

public interface OpencartAPIs {

    @FormUrlEncoded
    @POST("/index.php?route=api/account/register")
    Call<ReginfoResponse> register(@Field("firstname")String firstname,
                                   @Field("lastname")String lastname,
                                   @Field("email")String email,
                                   @Field("telephone")String telephone,
                                   @Field("password") String password,
                                   @Field("confirm") String confirm);

    @FormUrlEncoded
    @POST("/index.php?route=api/account/login")
    Call<LogininfoResponse> login(@Field("email")String email,
                                   @Field("password")String password);
}
