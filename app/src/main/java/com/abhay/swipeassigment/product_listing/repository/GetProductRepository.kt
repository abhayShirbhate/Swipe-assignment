package com.abhay.swipeassigment.product_listing.repository

import com.abhay.swipeassigment.product_listing.listener.GetProductAPIResponse

interface GetProductRepository {
    fun getProducts(getProductAPIResponse: GetProductAPIResponse)
}