package com.abhay.swipeassigment.product_listing.binding_adapter

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhay.swipeassigment.R
import com.abhay.swipeassigment.product_listing.adapter.ProductListingAdapter
import com.abhay.swipeassigment.product_listing.listener.IOnProductiListingHandler
import com.abhay.swipeassigment.product_listing.model.Product
import com.abhay.swipeassigment.product_listing.viewmodel.ProductListingViewModel
import com.bumptech.glide.Glide

object ProductListingBindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.loadImage( imageUrl: String?) {
        if (imageUrl != null) {
            Glide.with(context)
                .load(imageUrl)
//                .placeholder()
                .error(R.drawable.img_product)
                .into(this)

        }
    }

    @JvmStatic

    @BindingAdapter("setProductList","lifeCycleOwner")
    fun RecyclerView.setProductList(viewModel: ProductListingViewModel,lifecycleOwner: LifecycleOwner){
        viewModel.productListLiveData.observe(lifecycleOwner){productList->
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            adapter = getAdapter(productList)
        }
    }


    fun getAdapter(productList : List<Product>):ProductListingAdapter{
        return ProductListingAdapter(productList)
    }
    @JvmStatic
    @SuppressLint("ClickableViewAccessibility")
    @BindingAdapter("setEndDrawableListener")
    fun EditText.setEndDrawableListener(iOnProductiListingHandler: IOnProductiListingHandler){
        setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                if (p1?.action == MotionEvent.ACTION_UP) {
                    val clearButtonDrawable = compoundDrawablesRelative[2]
                    if (p1.rawX >= right - clearButtonDrawable.bounds.width()) {
                        iOnProductiListingHandler.clearSearch()
                        text?.clear()
                        return true
                    }
                }
                return false

            }

        })
    }
    @JvmStatic
    @BindingAdapter("setSearchButtonListener","searchEditText")
    fun Button.setSearchButtonListener(iOnProductiListingHandler: IOnProductiListingHandler,editText: EditText){
        setOnClickListener {
            iOnProductiListingHandler.searchItem(editText.text.toString())
        }
    }
}