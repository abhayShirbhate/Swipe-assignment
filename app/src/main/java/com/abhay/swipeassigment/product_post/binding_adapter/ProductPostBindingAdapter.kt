package com.abhay.swipeassigment.product_post.binding_adapter

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.Log
import android.view.PointerIcon
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import com.abhay.swipeassigment.R
import com.abhay.swipeassigment.product_post.listeners.IOnProductPostHandler
import com.abhay.swipeassigment.product_post.viewmodel.ProductPostViewModel
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputLayout

object ProductPostBindingAdapter {

    @JvmStatic
    @BindingAdapter("setAddProductListener","viewmodel")
    fun Button.setAddButtonListener(iOnHandler: IOnProductPostHandler,viewModel: ProductPostViewModel){
        setOnClickListener {
            val res: Resources by lazy { context.resources }
            var isInputesValides = true

            if((viewModel.productName.get()?.length ?: 0) > 3){
                isInputesValides = false
                viewModel.productNameError.set(res.getString(R.string.enter_valid_input_error_lable))
            }else viewModel.productNameError.set("")

            if((viewModel.productType.get()?:0) > 0){
                isInputesValides = false
                viewModel.productTypeError.set(res.getString(R.string.select_product_type_error_lable))
            }else viewModel.productTypeError.set("")

            if((viewModel.productPrice.get()?.length ?: 0) > 0){
                isInputesValides = false
                viewModel.productPriceError.set(res.getString(R.string.enter_valid_input_error_lable))
            }else viewModel.productPriceError.set("")

            if((viewModel.taxValue.get()?.length ?: 0) > 0){
                isInputesValides = false
                viewModel.taxValueError.set(res.getString(R.string.enter_valid_input_error_lable))
            }else viewModel.taxValueError.set("")

            if (isInputesValides) iOnHandler.postProduct(context)
        }
    }


    @JvmStatic
    @BindingAdapter(
        "setProductTypeListener"
    )
    fun AutoCompleteTextView.setAutocompleteTextviewList(
        viewmodel: ProductPostViewModel
    ) {

        val array = context.resources.getStringArray(R.array.product_type_arr)

        setText(array[0])
        setAdapter(
            ArrayAdapter(context, R.layout.list_item, array)
        )

        setOnItemClickListener { _, _, selectedPosition, _ ->
            Log.e("selectedPosition", selectedPosition.toString())
            viewmodel.productType.set(selectedPosition)
            if (selectedPosition == 0) {
                viewmodel.productTypeError.set(resources.getString(R.string.select_product_type_error_lable))
            } else {
                viewmodel.productTypeError.set("")
            }
            dismissDropDown()
            clearFocus()
        }

//        val background = ColorDrawable(Color.WHITE)
//        setDropDownBackgroundDrawable(background)
//        pointerIcon = PointerIcon.getSystemIcon(context, PointerIcon.TYPE_ARROW)
    }


    @JvmStatic
    @BindingAdapter("setImagePicker","viewmodel","image")
    fun View.SetImagePicker(ionHandler: IOnProductPostHandler,viewmodel: ProductPostViewModel,imageView: ImageView){
        setOnClickListener {
            ionHandler.onAddImageClick()
        }

        viewmodel.image.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                viewmodel.image.get()?.let {
                    Log.d("Image!!", "onPropertyChanged: $it")
                    Glide.with(context)
                        .load(it)
                        .into(imageView)
                }
            }
        })

    }

//    @JvmStatic
//    @BindingAdapter("setAddProductListener")
//    fun Button.setAddButtonListener(iOnHandler: IOnProductPostHandler){
//        setOnClickListener { iOnHandler.postProduct(context) }
//    }

}