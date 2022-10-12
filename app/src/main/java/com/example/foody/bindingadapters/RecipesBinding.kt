package com.example.foody.bindingadapters

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.foody.data.database.entities.RecipesEntity
import com.example.foody.models.FoodRecipe
import com.example.foody.util.NetworkResult


object RecipesBinding {

    @BindingAdapter("readApiResponse", "readDatabase", requireAll = true)
    @JvmStatic
    fun errorViewVisibility(
        view: View,
        apiResponse: NetworkResult<FoodRecipe>?,
        database: List<RecipesEntity>?
    ) {
        if (apiResponse is NetworkResult.Error && database.isNullOrEmpty()) {
            view.visibility = View.VISIBLE
            if (view is TextView) {
                view.text = apiResponse.message.toString()
            }
        } else {
            view.visibility = View.INVISIBLE
        }
    }
}
