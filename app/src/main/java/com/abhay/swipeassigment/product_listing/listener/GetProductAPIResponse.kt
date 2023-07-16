package com.abhay.swipeassigment.product_listing.listener

import com.abhay.swipeassigment.product_listing.model.Product
import com.abhay.swipeassigment.product_listing.response.GetProductAPIRespondState

interface GetProductAPIResponse {
    fun GetProductSuccessAPIListener(response : GetProductAPIRespondState.Success)
    fun GetProductFailureAPIListener(response : GetProductAPIRespondState)
}