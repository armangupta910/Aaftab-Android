package com.example.moksha

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2


class homeFagment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val x = inflater.inflate(R.layout.fragment_home_fagment, container, false)

        var viewPager: ViewPager2
        var dotIndicator: RecyclerView
        var dotIndicatorAdapter: DotIndicatorAdapter

        viewPager = x.findViewById(R.id.viewPager)
        dotIndicator = x.findViewById(R.id.dotsIndicator)

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
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

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

        return x
    }

}