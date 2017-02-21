package com.cviac.olaichuvadi.services;

import com.cviac.olaichuvadi.datamodels.AddToCartResponse;
import com.cviac.olaichuvadi.datamodels.AddressInfo;
import com.cviac.olaichuvadi.datamodels.CategoriesResponse;
import com.cviac.olaichuvadi.datamodels.CategoryProductsResponse;
import com.cviac.olaichuvadi.datamodels.GeneralResponse;
import com.cviac.olaichuvadi.datamodels.GetCartItemsResponse;
import com.cviac.olaichuvadi.datamodels.LoginResponse;
import com.cviac.olaichuvadi.datamodels.Productdetailresponse;

import java.util.HashMap;
import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;


public interface OpencartAPIs {

    @FormUrlEncoded
    @POST("/index.php?route=api/account/register")
    Call<ReginfoResponse> register(@Field("firstname") String firstname,
                                   @Field("lastname") String lastname,
                                   @Field("email") String email,
                                   @Field("telephone") String telephone,
                                   @Field("password") String password,
                                   @Field("confirm") String confirm);

    @FormUrlEncoded
    @POST("/index.php?route=api/account/login")
    Call<LogininfoResponse> login(@Field("email") String email,
                                  @Field("password") String password);

    @GET("/index.php?route=api/category")
    Call<CategoriesResponse> getCategories();

    @GET("/index.php?route=api/category/getproducts")
    Call<CategoryProductsResponse> getProducts(@Header("Authorization") String credentials,
                                               @Query("categoryid") String categoryid);

    @GET("/index.php?route=api/category/getproductdetails")
    Call<Productdetailresponse> getProductdetails(@Query("productid") String categoryid);

    @FormUrlEncoded
    @POST("/index.php?route=api/login")
    Call<LoginResponse> login(@Field("key") String apikey);

    @FormUrlEncoded
    @POST("/index.php?route=api/cart/add")
    Call<AddToCartResponse> addToCart(@Query("token") String token,
                                      @Field("product_id") String prodid,
                                      @Field("quantity") String quantity
    );

    @GET("/index.php?route=api/cart/products")
    Call<GetCartItemsResponse> getCartItems(@Query("token") String token);

    @GET("/index.php?route=api/address/getList")
    Call<List<AddressInfo>> getAdresses(@Query("customer_id") String customer_id);

    @POST("/index.php?route=api/address/add")
    Call<List<AddressInfo>> addAddress(@Field("customer_id") String customer_id,
                                       @Field("firstname") String firstname,
                                       @Field("lastname") String lastname,
                                       @Field("company") String company,
                                       @Field("address_1") String address_1,
                                       @Field("address_2") String address_2,
                                       @Field("postcode") String postcode,
                                       @Field("city") String city,
                                       @Field("zone") String zone,
                                       @Field("country") String country,
                                       @Field("zonecode") String zonecode,
                                       @Field("country_id") String country_id);

    @POST("/index.php?route=api/address/edit")
    Call<GeneralResponse> editAddress(@Field("address_id") String address_id,
                                      @Field("customer_id") String customer_id,
                                      @FieldMap HashMap<String, String> hashfields);

    @GET("/index.php?route=api/address/delete")
    Call<GeneralResponse> deleteAddress(@Query("address_id") String address_id,
                                        @Query("customer_id") String customer_id);
}