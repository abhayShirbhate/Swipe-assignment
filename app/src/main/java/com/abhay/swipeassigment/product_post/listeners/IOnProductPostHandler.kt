package com.abhay.swipeassigment.product_post.listeners

import android.content.Context

interface IOnProductPostHandler {
    fun postProduct(context: Context)
    fun onAddImageClick()
}