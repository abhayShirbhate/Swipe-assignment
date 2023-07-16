package com.abhay.swipeassigment.product_listing.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhay.swipeassigment.product_listing.listener.GetProductAPIResponse
import com.abhay.swipeassigment.product_listing.listener.IOnProductListingListener
import com.abhay.swipeassigment.product_listing.listener.IOnProductiListingHandler
import com.abhay.swipeassigment.product_listing.model.Product
import com.abhay.swipeassigment.product_listing.repository.GetProductRepository
import com.abhay.swipeassigment.product_listing.response.GetProductAPIRespondState
import kotlinx.coroutines.launch

class ProductListingViewModel(val repository: GetProductRepository): ViewModel(),GetProductAPIResponse,IOnProductiListingHandler {
    lateinit var iOnListener: IOnProductListingListener

    private var _productListLiveData = MutableLiveData<List<Product>>()
    val productListLiveData get() = _productListLiveData

    private var _getProductAPIErrorLiveData = MutableLiveData<String>()
    val getProductAPIErrorLiveData get() = _getProductAPIErrorLiveData

    var storeProductList : List<Product>? = null

    fun getProducts(){
        repository.getProducts(this)
    }

    override fun searchItem(searchText:String){
        val newProductList = ArrayList<Product>()
        viewModelScope.launch {
            productListLiveData.value?.forEach {item->
            if(item.productName.contains(searchText) || item.productType.contains(searchText))  {
                newProductList.add(item)
            }

            }
            storeProductList = productListLiveData.value
            productListLiveData.postValue(newProductList.toList())
        }
    }

    override fun clearSearch(){
        storeProductList?.let {
            productListLiveData.postValue(it)
            storeProductList = null
        }
    }

    override fun navigateToAddProductFragment() {
        iOnListener.navigateToAddProductFragment()
    }

    override fun GetProductSuccessAPIListener(response: GetProductAPIRespondState.Success) {
        productListLiveData.postValue(response.apiResponseList)

    }

    override fun GetProductFailureAPIListener(response: GetProductAPIRespondState) {
        if (response is GetProductAPIRespondState.InternetError){
            getProductAPIErrorLiveData.postValue("Something went wrong, Please try again")
        } else getProductAPIErrorLiveData.postValue("Something went wrong, Please try again")
    }
}