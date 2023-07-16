package com.abhay.swipeassigment.retrofit_impl

import com.abhay.swipeassigment.product_listing.model.Product
import com.abhay.swipeassigment.product_post.model.PostProductModel
import com.abhay.swipeassigment.product_post.model.PostProductResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("public/get")
    fun getProduct(): Call<List<Product>>
    @POST("public/add")
    fun postProduct(@Body postProductModel: PostProductModel): Call<PostProductResponse>
}