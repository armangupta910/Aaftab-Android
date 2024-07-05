package com.example.moksha

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class getStarted : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var dotIndicator: RecyclerView
    private lateinit var dotIndicatorAdapter: DotIndicatorAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        viewPager = findViewById(R.id.viewPager)
        dotIndicator = findViewById(R.id.dotsIndicator)

        val carouselItems = listOf(
            CarouselItem(R.drawable.personimage, "Item 1"),
            CarouselItem(R.drawable.personimage, "Item 2"),
            CarouselItem(R.drawable.personimage, "Item 3")
        )

        val adapter = CarouselAdapter(carouselItems)
        viewPager.adapter = adapter

        // Set up dot indicator
        dotIndicatorAdapter = DotIndicatorAdapter(carouselItems.size)
        dotIndicator.adapter = dotIndicatorAdapter
        dotIndicator.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Attach Page Change Listener to ViewPager2
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                dotIndicatorAdapter.setSelectedIndex(position)
            }
        })

        val space = 40 // Adjust this value to control the space between items
        viewPager.addItemDecoration(CarouselItemDecoration(space))
        viewPager.setPageTransformer(CarouselPageTransformer(0.8f))
    }
}