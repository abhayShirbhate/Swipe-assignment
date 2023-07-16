package com.abhay.swipeassigment.product_listing.response

import com.abhay.swipeassigment.product_listing.model.Product

sealed class GetProductAPIRespondState{
    data class Success(val apiResponseList: List<Product>) : GetProductAPIRespondState()
    data class Error(val errorMessage: String) : GetProductAPIRespondState()
    data class InternetError(val errorMessage: String) : GetProductAPIRespondState()
}
