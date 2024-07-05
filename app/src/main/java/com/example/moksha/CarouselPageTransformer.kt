package com.example.moksha

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class CarouselPageTransformer(private val scaleFactor: Float) : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.scaleY = 1 - (0.1f * Math.abs(position))
        page.alpha = 0.8f + (1 - Math.abs(position)) * 0.2f
    }
}
