package com.example.spacex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex.databinding.RocketLayoutBinding
import com.squareup.picasso.Picasso

class RocketImageAdapter : RecyclerView.Adapter<RocketImageAdapter.ViewHolder>() {

    private var rocketImageList = ArrayList<String>()

    fun setRocketImageList(rocketImageList: List<String>) {
        this.rocketImageList = rocketImageList as ArrayList<String>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: RocketLayoutBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RocketLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context,
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.get()
            .load(rocketImageList[position])
            .into(holder.binding.image)

    }

    override fun getItemCount(): Int {
        return rocketImageList.size
    }

}