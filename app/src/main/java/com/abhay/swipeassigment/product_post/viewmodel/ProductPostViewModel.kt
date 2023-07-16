package com.abhay.swipeassigment.product_post.viewmodel

import android.content.Context
import android.net.Uri
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhay.swipeassigment.R
import com.abhay.swipeassigment.product_listing.model.Product
import com.abhay.swipeassigment.product_post.listeners.IOnPostProductListener
import com.abhay.swipeassigment.product_post.listeners.IOnProductPostHandler
import com.abhay.swipeassigment.product_post.listeners.PostProductAPIResponse
import com.abhay.swipeassigment.product_post.model.PostProductModel
import com.abhay.swipeassigment.product_post.repository.PostProductRepository
import com.abhay.swipeassigment.product_post.response.PostProductResponseState
import com.abhay.swipeassigment.utils.Utils

class ProductPostViewModel(private val repository: PostProductRepository):ViewModel(),IOnProductPostHandler,PostProductAPIResponse {
    lateinit var iOnListener: IOnPostProductListener

    val image = ObservableField<Uri>()
    val productName = ObservableField<String>()
    val productType = ObservableField<Int>()
    val productPrice = ObservableField<String>()
    val taxValue = ObservableField<String>()

    val productNameError = ObservableField<String>()
    val productTypeError = ObservableField<String>()
    val productPriceError = ObservableField<String>()
    val taxValueError = ObservableField<String>()

    private val _postProductAPISuccessLiveData = MutableLiveData<String>()
    val  postProductAPISuccessLiveData get() = _postProductAPISuccessLiveData

    private val _postProductAPIFailureLiveData = MutableLiveData<String>()
    val  postProductAPIFailureLiveData get() = _postProductAPIFailureLiveData

    override fun postProduct(context: Context) {
        repository.postProduct(
            PostProductModel(
                Utils.createImagePartFromPermanentUri(context,image.get()!!)!!,
                productPrice.get()!!.toDouble(),
                productName.get()!!,
                context.resources.getStringArray(R.array.product_type_arr)[productType.get()!!],
                taxValue.get()!!.toDouble()
            ),
            this
        )
    }

    override fun onPostProductSuccessAPIResponse(response: PostProductResponseState.Success) {
        postProductAPISuccessLiveData.postValue(response.postProductResponse.message)
    }

    override fun onPostProductFailureAPIResponse(response: PostProductResponseState) {
        if (response is PostProductResponseState.InternetError){
            postProductAPIFailureLiveData.postValue("Please check your device internet connection and try again")
        }else if(response is PostProductResponseState.Error){
            postProductAPIFailureLiveData.postValue(response.errorMessage)
        }
    }

    override fun onAddImageClick() {
        iOnListener.onAddImageClick()
    }


}