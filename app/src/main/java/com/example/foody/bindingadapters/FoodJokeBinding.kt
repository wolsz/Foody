package com.example.foody.bindingadapters

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.foody.data.database.entities.FoodJokeEntity
import com.example.foody.models.FoodJoke
import com.example.foody.util.NetworkResult
import com.google.android.material.card.MaterialCardView

object FoodJokeBinding {

    @BindingAdapter("readApiResponseJok", "readDatabaseJok", requireAll = false)
    @JvmStatic
    fun setCardAndProgressVisibility(
        view: View,
        apiResponse: NetworkResult<FoodJoke>,
        database: List<FoodJokeEntity>?
        ) {
        when(apiResponse) {
            is NetworkResult.Loading -> {
                when (view) {
                    is ProgressBar -> {
                        view.visibility = View.VISIBLE
                    }
                    is MaterialCardView -> {
                        view.visibility = View.INVISIBLE
                    }
                }
            }
            is NetworkResult.Error -> {
                when (view) {
                    is ProgressBar -> {
                        view.visibility = View.INVISIBLE
                    }
                    is MaterialCardView -> {
                        view.visibility = View.VISIBLE
                        if (database.isNullOrEmpty()) {
                            view.visibility = View.INVISIBLE
                        }
                    }
                }
            }
            is NetworkResult.Success -> {
                when(view) {
                    is ProgressBar -> {
                        view.visibility = View.INVISIBLE
                    }
                    is MaterialCardView -> {
                        view.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    @BindingAdapter("readApiResponseJok1", "readDatabaseJok1", requireAll = true)
    @JvmStatic
    fun setErrorVisibility(
        view: View,
        apiResponse: NetworkResult<FoodJoke>?,
        database: List<FoodJokeEntity>?
    ) {
        if (apiResponse is NetworkResult.Loading) {
            view.visibility = View.INVISIBLE
        } else
        if (database.isNullOrEmpty()){
            view.visibility = View.VISIBLE
            if (view is TextView) {
                if (apiResponse != null) {
                    view.text = apiResponse.message.toString()
                }
            }
        }
        if (apiResponse is NetworkResult.Success) {
            view.visibility = View.INVISIBLE
        }
    }
}