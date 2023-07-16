package com.abhay.swipeassigment.product_post.fragment

import android.app.Activity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abhay.swipeassigment.databinding.ProductPostFragmentBinding
import com.abhay.swipeassigment.product_post.listeners.IOnPostProductListener
import com.abhay.swipeassigment.product_post.viewmodel.ProductPostViewModel
import com.abhay.swipeassigment.utils.Utils
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.github.dhaval2404.imagepicker.ImagePicker

class ProductPostFragment: Fragment(),IOnPostProductListener {
    lateinit var binding : ProductPostFragmentBinding
    val viewModel by viewModel<ProductPostViewModel>()


    val registerForPickProfileImage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imagePath = result.data?.data
                Log.d("Image!!", "onPropertyChanged: $imagePath")
                viewModel.image.set(imagePath)
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProductPostFragmentBinding.inflate(inflater,container,false)
        initBinding()

        return binding.root
    }

    private fun initBinding(){
        binding.viewModel = viewModel
        binding.iOnHandler = viewModel
        viewModel.iOnListener = this
        addPostProductAPILiveDataObserver()

    }

    private fun addPostProductAPILiveDataObserver(){
        viewModel.postProductAPISuccessLiveData.observe(this){
            showDialogMessage(it){
                findNavController().popBackStack()
            }
        }
        viewModel.postProductAPIFailureLiveData.observe(this){
            showDialogMessage(it){}
        }
    }

    override fun onAddImageClick() {
        ImagePicker.with(this)
            .crop(1F, 1F)
            .saveDir(requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!)
            .compress(1024)
            .createIntent {
                registerForPickProfileImage.launch(it)
            }
    }

    fun showDialogMessage(error: String,listener: ()->Unit) {
        Utils.showMessageDialog(requireContext(),"Alert",error,listener)
    }
}