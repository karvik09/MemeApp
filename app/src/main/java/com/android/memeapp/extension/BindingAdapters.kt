package com.android.memeapp.extension

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.databinding.BindingAdapter
import coil.load


@BindingAdapter("url", "placeholder")
fun ImageView.loadImage(url: String, placeholder: Int) {
    load(url) {
        crossfade(true)
        placeholder(placeholder)
        error(placeholder)
    }
}

@BindingAdapter("dimensionRatio")
fun View.setDimensionRatio(ratio: String) {
    this.updateLayoutParams<ConstraintLayout.LayoutParams> {
        dimensionRatio = ratio
    }
}