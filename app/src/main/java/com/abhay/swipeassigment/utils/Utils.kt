package com.abhay.swipeassigment.utils

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import okhttp3.*
import java.io.File

object Utils {
    fun showMessageDialog(context: Context, title: String, message: String,listener: ()-> Unit) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            listener()
        }
        val dialog = builder.create()
        dialog.show()
    }
    fun createImagePartFromPermanentUri(context: Context,permanentUri: Uri): MultipartBody.Part? {
        val file: File = File(permanentUri.path!!)
        // Create request body for the file
//        val requestBody: RequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())

        val requestFile = RequestBody.create(
            MediaType.parse("multipart/form-data"),
            file
        )
        // Create MultipartBody.Part from request body
        return MultipartBody.Part.createFormData("image", file.name, requestFile)
    }

    fun intercept(chain: Interceptor.Chain): Response {
        // Add your interception logic here
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer your_token_here")
            .build()

        return chain.proceed(request)
    }
}