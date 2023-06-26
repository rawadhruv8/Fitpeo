package com.app.fitpeo.util

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter

import com.squareup.picasso.Picasso
import java.io.File

class CustomBindingAdapters {
   companion object {

       @JvmStatic
       @BindingAdapter("fileUrl")
       fun loadResourceImage(view: ImageView, fileUrl: String?) {
           Log.d("picasso testing--->>", fileUrl.toString())
           if (!fileUrl.isNullOrEmpty()) Picasso.get().load(fileUrl).into(view)
       }

   }
}

