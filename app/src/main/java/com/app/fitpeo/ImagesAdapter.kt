package com.app.fitpeo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.fitpeo.databinding.AdapterImagesItemBinding
import com.app.fitpeo.retrofit.ResponseImages


class ImagesAdapter(var list: ArrayList<ResponseImages>, var listener: (ResponseImages) -> Unit) :
    RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: AdapterImagesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(item: ResponseImages) {

            binding.apply {
                viewModel = item
                executePendingBindings()

                root.setOnClickListener {
                    listener(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterImagesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position].let {
            holder.bindData(it)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}


