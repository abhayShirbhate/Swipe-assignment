package com.abhay.swipeassigment.product_listing.repository

import com.abhay.swipeassigment.product_listing.listener.GetProductAPIResponse
import com.abhay.swipeassigment.product_listing.model.Product
import com.abhay.swipeassigment.product_listing.response.GetProductAPIRespondState
import com.abhay.swipeassigment.retrofit_impl.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class GetProductsRepositoryImpl(val api: ApiService): GetProductRepository {
    override fun getProducts(getProductAPIResponse: GetProductAPIResponse) {
        api.getProduct().enqueue(object : Callback<List<Product>> {
            override fun onResponse(
                call: Call<List<Product>>,
                response: Response<List<Product>>
            ) {
                if (response.isSuccessful && response.body() != null){
                    getProductAPIResponse.GetProductSuccessAPIListener(GetProductAPIRespondState.Success(response.body()!!))
                }else getProductAPIResponse.GetProductFailureAPIListener(GetProductAPIRespondState.Error("NA"))

            }
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                if (t is SocketTimeoutException){
                    getProductAPIResponse.GetProductFailureAPIListener(GetProductAPIRespondState.InternetError(t.message?:"NA"))
                }else getProductAPIResponse.GetProductFailureAPIListener(GetProductAPIRespondState.Error(t.message?:"NA"))
            }
        })
    }
}