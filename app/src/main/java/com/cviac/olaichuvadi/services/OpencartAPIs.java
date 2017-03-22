package com.cviac.olaichuvadi.services;

import com.cviac.olaichuvadi.datamodels.AddToCartResponse;
import com.cviac.olaichuvadi.datamodels.AddressInfo;
import com.cviac.olaichuvadi.datamodels.AuthorsResponse;
import com.cviac.olaichuvadi.datamodels.CategoriesResponse;
import com.cviac.olaichuvadi.datamodels.CategoryProductsResponse;
import com.cviac.olaichuvadi.datamodels.ClubResponse;
import com.cviac.olaichuvadi.datamodels.CountryInfo;
import com.cviac.olaichuvadi.datamodels.GeneralResponse;
import com.cviac.olaichuvadi.datamodels.GetCartItemsResponse;
import com.cviac.olaichuvadi.datamodels.LoginResponse;
import com.cviac.olaichuvadi.datamodels.PaymentMethodsResponse;
import com.cviac.olaichuvadi.datamodels.GetGetPostMessagesResponse;
import com.cviac.olaichuvadi.datamodels.Productdetailresponse;
import com.cviac.olaichuvadi.datamodels.PublishersResponse;
import com.cviac.olaichuvadi.datamodels.ShippingMethodsResponse;
import com.cviac.olaichuvadi.datamodels.ZoneInfo;
import com.squareup.okhttp.ResponseBody;

import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
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
    Call<CategoryProductsResponse> getProducts(@Query("categoryid") String categoryid);

    @GET("/index.php?route=api/search")
    Call<CategoryProductsResponse> search(@Query("key") String key);

    @GET("/index.php?route=api/category/getproductdetails")
    Call<Productdetailresponse> getProductdetails(@Query("productid") String categoryid);

    @FormUrlEncoded
    @POST("/index.php?route=api/login")
    Call<LoginResponse> login(@Field("key") String apikey);

    @FormUrlEncoded
    @POST("/index.php?route=api/cart/add")
    Call<AddToCartResponse> addToCart(@Field("product_id") String prodid,
                                      @Field("quantity") String quantity);

    @FormUrlEncoded
    @POST("/index.php?route=api/cart/remove")
    Call<AddToCartResponse> removeFromCart(@Field("key") String cartid);

    @GET("/index.php?route=api/cart/products")
    Call<GetCartItemsResponse> getCartItems();

    @FormUrlEncoded
    @POST("/index.php?route=api/address/add")
    Call<GeneralResponse> addAddress(@Field("customer_id") String customer_id,
                                     @Field("firstname") String firstname,
                                     @Field("lastname") String lastname,
                                     @Field("company") String company,
                                     @Field("address_1") String address_1,
                                     @Field("address_2") String address_2,
                                     @Field("postcode") String postcode,
                                     @Field("city") String city,
                                     @Field("zone_id") String zonecode,
                                     @Field("country_id") String country_id);

    @FormUrlEncoded
    @POST("/index.php?route=api/address/edit")
    Call<GeneralResponse> editAddress(@Field("address_id") String address_id,
                                      @Field("customer_id") String customer_id,
                                      @Field("firstname") String firstname,
                                      @Field("lastname") String lastname,
                                      @Field("company") String company,
                                      @Field("address_1") String address_1,
                                      @Field("address_2") String address_2,
                                      @Field("postcode") String postcode,
                                      @Field("city") String city,
                                      @Field("zone_id") String zone_id,
                                      @Field("country_id") String country_id);

    @GET("/index.php?route=api/address/getList")
    Call<List<AddressInfo>> getAdresses(@Query("customer_id") String customer_id);

    @GET("/index.php?route=api/address/delete")
    Call<GeneralResponse> deleteAddress(@Query("address_id") String address_id,
                                        @Query("customer_id") String customer_id);

    @GET("/index.php?route=api/address/getCountries")
    Call<List<CountryInfo>> getCountries();

    @GET("/index.php?route=api/address/getZones")
    Call<List<ZoneInfo>> getZones(@Query("country_id") String country_id);

    @GET("/index.php?route=api/payment/methods")
    Call<PaymentMethodsResponse> getPaymentMethods();

    @GET("/index.php?route=api/shipping/methods")
    Call<ShippingMethodsResponse> getShippingMethods();

    // Order Placement APIs
    @FormUrlEncoded
    @POST("/index.php?route=api/customer")
    Call<GeneralResponse> setCustomerSession(@Field("customer_id") String customer_id,
                                             @Field("customer_group_id") String customer_group_id,
                                             @Field("firstname") String firstname,
                                             @Field("lastname") String lastname,
                                             @Field("email") String email,
                                             @Field("telephone") String telephone,
                                             @Field("fax") String fax);

    @FormUrlEncoded
    @POST("/index.php?route=api/payment/address")
    Call<GeneralResponse> setPaymentAddress(@Field("firstname") String firstname,
                                            @Field("lastname") String lastname,
                                            @Field("company") String company,
                                            @Field("address_1") String address_1,
                                            @Field("address_2") String address_2,
                                            @Field("postcode") String postcode,
                                            @Field("city") String city,
                                            @Field("zone_id") String zone_id,
                                            @Field("country_id") String country_id);

    @FormUrlEncoded
    @POST("/index.php?route=api/payment/method")
    Call<GeneralResponse> setPaymentMethod(@Field("payment_method") String payment_method);

    @FormUrlEncoded
    @POST("/index.php?route=api/shipping/address")
    Call<GeneralResponse> setShippingAddress(@Field("firstname") String firstname,
                                             @Field("lastname") String lastname,
                                             @Field("company") String company,
                                             @Field("address_1") String address_1,
                                             @Field("address_2") String address_2,
                                             @Field("postcode") String postcode,
                                             @Field("city") String city,
                                             @Field("zone_id") String zone_id,
                                             @Field("country_id") String country_id);

    @FormUrlEncoded
    @POST("/index.php?route=api/shipping/method")
    Call<GeneralResponse> setShippingMethod(@Field("shipping_method") String shipping_method);

    @FormUrlEncoded
    @POST("/index.php?route=api/order/add")
    Call<ResponseBody> placeOrder(@Field("payment_method") String payment_method,
                                  @Field("shipping_method") String shipping_method,
                                  @Field("comment") String comment,
                                  @Field("affiliate_id") String affiliate_id,
                                  @Field("order_status_id") String order_status_id);

    //My Community APIs
    @GET("/index.php?route=api/mycommunity/getRecommendedClubs")
    Call<ClubResponse> getRecommendedClubs();

    @GET("/index.php?route=api/mycommunity/getJoinedClubs")
    Call<ClubResponse> getJoinedClubs();

    @GET("/index.php?route=api/mycommunity/getMyClubs")
    Call<ClubResponse> getMyClubs();

    @POST("/index.php?route=api/mycommunity/createClub")
    Call<GeneralResponse> newClub(@Field("club_name") String club_name,
                                  @Field("club_description") String club_description,
                                  @Field("status") String status,
                                  @Field("location") String location);

    @POST("/index.php?route=api/mycommunity/joinClub")
    Call<GeneralResponse> joinClub(@Field("group_id") String group_id);

    @GET("/index.php?route=api/mycommunity/getPostMessages")
    Call<GetGetPostMessagesResponse> getPost(@Query("group_id") String group_id);

    @POST("/index.php?route=api/mycommunity/inviteMembers")
    Call<GeneralResponse> inviteMembers(@Query("group_id") String group_id,
                                        @Field("email") String email);

    @GET("/index.php?route=api/mycommunity/searchAuthors")
    Call<AuthorsResponse> searchAuthor(@Query("name") String name);

    @POST("/index.php?route=api/mycommunity/likeAuthor")
    Call<GeneralResponse> likeAuthor(@Field("author_id") String author_id);

    @GET("/index.php?route=api/mycommunity/getLikedAuthors")
    Call<AuthorsResponse> getLikedAuthor();

    @GET("/index.php?route=api/mycommunity/searchPublishers")
    Call<PublishersResponse> searchPublisher(@Query("name") String name);

    @POST("/index.php?route=api/mycommunity/likePublisher")
    Call<GeneralResponse> likePublisher(@Field("publisher_id") String publisher_id);

    @GET("/index.php?route=api/mycommunity/getLikedPublishers")
    Call<PublishersResponse> getLikedPublisher();
}