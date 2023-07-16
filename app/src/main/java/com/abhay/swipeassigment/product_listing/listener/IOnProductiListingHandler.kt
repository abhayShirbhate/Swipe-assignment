package com.abhay.swipeassigment.product_listing.listener

interface IOnProductiListingHandler {
    fun searchItem(searchText:String)
    fun clearSearch()
    fun navigateToAddProductFragment()
}