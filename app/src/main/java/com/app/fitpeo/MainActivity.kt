package com.app.fitpeo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.app.fitpeo.databinding.ActivityMainBinding
import com.app.fitpeo.retrofit.ResponseImages
import com.app.fitpeo.util.ConnectionManager
import com.google.gson.Gson
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ImagesViewModel by inject()
    private val connectionManager: ConnectionManager by inject()

    companion object {
        const val IMAGE_DATA = "image_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        getOnlineImages()
        observeViewModel()
    }

    private fun getOnlineImages() {
        if (connectionManager.isOnline()) {
            viewModel.callImagesApi()
        } else {
            handelProgressVisibility(View.GONE)
            showToast(getString(R.string.error_no_internet))
        }
    }

    private fun observeViewModel() {
        viewModel.observeError.observe(this) {
            handelProgressVisibility(View.GONE)
            if (it is Int)
                showToast(getString(it))
            else
                showToast(it.toString())
        }

        viewModel.observeResponse.observe(this) {
            handelProgressVisibility(View.GONE)
            initImagesAdapter(it)
        }
    }

    private fun handelProgressVisibility(visibility: Int) {
        binding.progressBar.visibility = visibility
    }

    private fun initImagesAdapter(it: ArrayList<ResponseImages>) {
        binding.rvImages.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)

            adapter = ImagesAdapter(it) {
                val intent = Intent(this@MainActivity, ImageDetailActivity::class.java)
                intent.putExtra(IMAGE_DATA, Gson().toJson(it))
                startActivity(intent)
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}