package com.abhay.swipeassigment.product_post.repository

import android.util.Log
import com.abhay.swipeassigment.product_listing.model.Product
import com.abhay.swipeassigment.product_post.listeners.PostProductAPIResponse
import com.abhay.swipeassigment.product_post.model.PostProductModel
import com.abhay.swipeassigment.product_post.model.PostProductResponse
import com.abhay.swipeassigment.product_post.response.PostProductResponseState
import com.abhay.swipeassigment.retrofit_impl.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class PostProductsRepositoryImpl(val api: ApiService): PostProductRepository {
    override fun postProduct(productModel: PostProductModel,postProductAPIResponse: PostProductAPIResponse) {
        api.postProduct(productModel).enqueue(object : Callback<PostProductResponse> {
            override fun onResponse(
                call: Call<PostProductResponse>,
                response: Response<PostProductResponse>
            ) {
                if (response.isSuccessful && response.body() != null){
                    postProductAPIResponse.onPostProductSuccessAPIResponse(PostProductResponseState.Success(response.body()!!))
                }else {
                    postProductAPIResponse.onPostProductFailureAPIResponse(
                        PostProductResponseState.Error(
                            "NA"
                        )
                    )
                }

                Log.d("TAG!!", "onFailure: ${response.errorBody()}")
            }
            override fun onFailure(call: Call<PostProductResponse>, t: Throwable) {
                Log.d("TAG!!", "onFailure: ${t.message}")
                if (t is SocketTimeoutException){
                    postProductAPIResponse.onPostProductFailureAPIResponse(PostProductResponseState.InternetError(t.message?:"NA"))
                }else postProductAPIResponse.onPostProductFailureAPIResponse(PostProductResponseState.Error(t.message?:"NA"))
            }
        })
    }
}