package com.abhay.swipeassigment.product_post.repository

import com.abhay.swipeassigment.product_post.listeners.PostProductAPIResponse
import com.abhay.swipeassigment.product_post.model.PostProductModel

interface PostProductRepository {
    fun postProduct(product: PostProductModel,postProductAPIResponse: PostProductAPIResponse)
}