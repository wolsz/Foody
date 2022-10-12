package com.example.foody.bindingadapters

import android.accounts.AuthenticatorDescription
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.example.foody.R
import com.example.foody.models.Result
import com.example.foody.ui.fragments.recipes.RecipesFragmentDirections
import org.jsoup.Jsoup


object RecipesRowBinding {

    @BindingAdapter("applyVeganColor")
    @JvmStatic
    fun applyVeganColor(view: View, vegan: Boolean) {
        if (vegan) {
            when (view) {
                is TextView -> {
                    view.setTextColor(ContextCompat.getColor(view.context, R.color.green))
                }
                is ImageView -> {
                    view.setColorFilter(ContextCompat.getColor(view.context, R.color.green))
                }
            }
        }
    }

    @BindingAdapter("loadImageFromUrl")
    @JvmStatic
    fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
        imageView.load(imageUrl) {
            crossfade(600)
            error(R.drawable.ic_error_placeholder)
        }
    }

    @BindingAdapter("onRecipesClickListener")
    @JvmStatic
    fun onRecipesClickListener(recipeRowLayout: ConstraintLayout, result: Result) {
        recipeRowLayout.setOnClickListener {
            try {
                val action = RecipesFragmentDirections.actionRecipesFragmentToDetailsActivity(result)
                recipeRowLayout.findNavController().navigate(action)
            } catch (e: Exception) {
                Log.d("RecipesRowBinding", "onRecipesClickListener: ${e.toString()}")
            }
        }
    }

    @BindingAdapter("parseHtml")
    @JvmStatic
    fun parseHtml(textView: TextView, description: String?) {
        if (description != null) {
            val desc = Jsoup.parse(description).text()
            textView.text = desc
        }
    }
}
