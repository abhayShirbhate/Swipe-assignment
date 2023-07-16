package com.abhay.swipeassigment.product_post.listeners

import com.abhay.swipeassigment.product_post.model.PostProductResponse
import com.abhay.swipeassigment.product_post.response.PostProductResponseState

interface PostProductAPIResponse {
    fun onPostProductSuccessAPIResponse(response: PostProductResponseState.Success)
    fun onPostProductFailureAPIResponse(response: PostProductResponseState)
}