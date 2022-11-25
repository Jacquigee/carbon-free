package com.example.carbonfree.ui.fragments.main.getcarbonfree.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.carbonfree.databinding.CarbonFreeLayoutBinding
import com.example.carbonfree.ui.fragments.main.getcarbonfree.model.CarbonFree

class CarbonFreeAdapter (
    private val listener: ClickListener
) : RecyclerView.Adapter<CarbonFreeAdapter.MyViewHolder>() {


    inner class MyViewHolder(val binding: CarbonFreeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<CarbonFree>() {
        override fun areItemsTheSame(oldItem: CarbonFree, newItem: CarbonFree): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CarbonFree, newItem: CarbonFree): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallBack)


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.binding.apply {

            lottieAnim.setAnimation(currentItem.carbonFreeLottie)
            lottieAnim.playAnimation()
            textView.text = currentItem.carbonFreeName
        }


        holder.itemView.setOnClickListener {
            listener.onMyItemClick(currentItem)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CarbonFreeLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = differ.currentList.size

    interface ClickListener {
        // item on click listener
        fun onMyItemClick(carbonFree: CarbonFree)
    }
}