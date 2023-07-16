package com.abhay.swipeassigment.koin_module

import com.abhay.swipeassigment.product_listing.repository.GetProductRepository
import com.abhay.swipeassigment.product_listing.repository.GetProductsRepositoryImpl
import com.abhay.swipeassigment.product_listing.viewmodel.ProductListingViewModel
import com.abhay.swipeassigment.product_post.repository.PostProductRepository
import com.abhay.swipeassigment.product_post.repository.PostProductsRepositoryImpl
import com.abhay.swipeassigment.product_post.viewmodel.ProductPostViewModel
import com.abhay.swipeassigment.retrofit_impl.ApiService
import com.abhay.swipeassigment.utils.Utils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        val interceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                // Add any desired headers or modify the request as needed
                // For example, adding an authorization header:
                .addHeader("Authorization", "Bearer YOUR_AUTH_TOKEN")
                .build()
            chain.proceed(request)
        }

        Retrofit.Builder()
            .baseUrl("https://app.getswipe.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            )
            .build()
            .create(ApiService::class.java)
    }

    single<GetProductRepository> {
        GetProductsRepositoryImpl(get())
    }

    single<PostProductRepository> {
        PostProductsRepositoryImpl(get())
    }

    viewModel { ProductListingViewModel(get()) }
    viewModel { ProductPostViewModel(get()) }

}