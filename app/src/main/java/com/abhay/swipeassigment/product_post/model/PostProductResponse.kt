package com.abhay.swipeassigment.product_post.model

import com.abhay.swipeassigment.product_listing.model.Product
import com.google.gson.annotations.SerializedName

data class PostProductResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("product_details")
    val productDetails: Product,

    @SerializedName("product_id")
    val productId: Int,

    @SerializedName("success")
    val success: Boolean)
