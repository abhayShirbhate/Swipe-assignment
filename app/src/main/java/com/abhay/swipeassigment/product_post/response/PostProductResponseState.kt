package com.abhay.swipeassigment.product_post.response

import com.abhay.swipeassigment.product_post.model.PostProductResponse

sealed class PostProductResponseState{
    data class Success(val postProductResponse: PostProductResponse) : PostProductResponseState()
    data class Error(val errorMessage: String) : PostProductResponseState()
    data class InternetError(val errorMessage: String) : PostProductResponseState()
}
