package com.abhay.swipeassigment.product_listing.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abhay.swipeassigment.R
import com.abhay.swipeassigment.databinding.ProductAdapterItemBinding
import com.abhay.swipeassigment.product_listing.model.Product

class ProductListingAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductListingAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_adapter_item, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ProductAdapterItemBinding.bind(itemView)

        fun bind(product: Product) {
            binding.product = product
        }
    }
}
