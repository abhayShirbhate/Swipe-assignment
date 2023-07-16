package com.abhay.swipeassigment.product_post.model

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import retrofit2.http.Part

data class PostProductModel(
    @Part("image", encoding = "multipart/form-data")
    val image: MultipartBody.Part,

    @SerializedName("price")
    val price: Double,

    @SerializedName("product_name")
    val productName: String,

    @SerializedName("product_type")
    val productType: String,

    @SerializedName("tax")
    val tax: Double
)
