package com.abhay.swipeassigment.product_listing.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abhay.swipeassigment.R
import com.abhay.swipeassigment.databinding.ProductFragmentBinding
import com.abhay.swipeassigment.product_listing.listener.IOnProductListingListener
import com.abhay.swipeassigment.product_listing.viewmodel.ProductListingViewModel
import com.abhay.swipeassigment.utils.Utils
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListingFragment:Fragment(),IOnProductListingListener {
    val viewModel by viewModel<ProductListingViewModel>()
    lateinit var binding: ProductFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProductFragmentBinding.inflate(inflater,container,false)
        initBinding()
        return binding.root
    }

    private fun initBinding(){
        viewModel.iOnListener = this
        binding.viewModel = viewModel
        binding.iOnHandler = viewModel
        binding.lifeCycleOwner = this
        addGetProductAPIErrorObservable()
        viewModel.getProducts()
    }

    private fun addGetProductAPIErrorObservable(){
        viewModel.getProductAPIErrorLiveData.observe(this){
            showDialogMessage(it)
        }
    }

    fun showDialogMessage(error: String) {
        Utils.showMessageDialog(requireContext(),"Alert",error){}
    }

    override fun navigateToAddProductFragment() {
        findNavController().navigate(R.id.productPostFragment)
    }
}