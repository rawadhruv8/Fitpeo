package com.app.fitpeo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.fitpeo.databinding.ActivityImageDetailBinding
import com.app.fitpeo.retrofit.ResponseImages
import com.google.gson.Gson


class ImageDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding  = DataBindingUtil.setContentView(this, R.layout.activity_image_detail)

        setUpView()
    }

    private fun setUpView() {
        val data = Gson().fromJson(
            intent.getStringExtra(MainActivity.IMAGE_DATA),
            ResponseImages::class.java
        )

        binding.viewModel = data

    }
}