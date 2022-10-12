package com.example.foody.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foody.R
import com.example.foody.databinding.IngredientsRowLayoutBinding
import com.example.foody.models.ExtendedIngredient
import com.example.foody.util.Constants.Companion.BASE_IMAGE_URL
import com.example.foody.util.capitalized

class IngredientsAdapter :
    ListAdapter<ExtendedIngredient, IngredientsAdapter.MyViewHolder>(ExtendedIngredientDiffCallback) {

    class MyViewHolder private constructor(val binding: IngredientsRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ExtendedIngredient) {
            binding.ingredientImageView.load(BASE_IMAGE_URL + item.image) {
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
            binding.ingredientName.text = item.name?.capitalized() ?: ""
            binding.ingredientAmount.text = item.amount.toString()
            binding.ingredientUnit.text = item.unit
            binding.ingredientConsistency.text = item.consistency
            binding.ingredientOriginal.text = item.original
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = IngredientsRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

object ExtendedIngredientDiffCallback : DiffUtil.ItemCallback<ExtendedIngredient>() {
    override fun areItemsTheSame(
        oldItem: ExtendedIngredient,
        newItem: ExtendedIngredient
    ): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(
        oldItem: ExtendedIngredient,
        newItem: ExtendedIngredient
    ): Boolean {
        return oldItem == newItem
    }

}
