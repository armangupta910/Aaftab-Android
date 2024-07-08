package com.example.moksha

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class DotIndicatorAdapter(private val itemCount: Int) :
    RecyclerView.Adapter<DotIndicatorAdapter.DotViewHolder>() {

    private var selectedIndex = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DotViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dot_indicator_item, parent, false)
        return DotViewHolder(view)
    }

    override fun onBindViewHolder(holder: DotViewHolder, position: Int) {
        val context = holder.itemView.context

        if (position == selectedIndex) {
            holder.dot.setImageResource(R.drawable.dot_selected)

            val dpSizeInPixels1 = dpToPixels(context, 7) // 12dp, adjust as needed
            val dpSizeInPixels2 = dpToPixels(context,14)
            val layoutParams = holder.dot.layoutParams
            layoutParams.width = dpSizeInPixels2
            layoutParams.height = dpSizeInPixels1
            holder.dot.layoutParams = layoutParams
        } else {
            holder.dot.setImageResource(R.drawable.dot_unselected)
            val dpSizeInPixels = dpToPixels(context, 7) // 12dp, adjust as needed
            val layoutParams = holder.dot.layoutParams
            layoutParams.width = dpSizeInPixels
            layoutParams.height = dpSizeInPixels
            holder.dot.layoutParams = layoutParams
        }
    }

    private fun dpToPixels(context: Context, dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return (dp.toFloat() * density).toInt()

    }


        override fun getItemCount(): Int {
            return itemCount
        }

    fun setSelectedIndex(selectedIndex: Int) {
        this.selectedIndex = selectedIndex
        notifyDataSetChanged()
    }

    class DotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dot: ImageView = itemView.findViewById(R.id.dot)
    }
}
