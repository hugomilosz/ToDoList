package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.CheckBox
import android.widget.TextView

class ItemAdapter(
    private val items: MutableList<Item>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item,
                parent,
                false
            )
        )
    }

    fun addItem(item: Item) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun deleteDoneItems() {
        items.removeAll { item ->
            item.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(itemName: TextView, isChecked: Boolean) {
        if (isChecked) {
            itemName.paintFlags = itemName.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            itemName.paintFlags = itemName.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items.get(position)
        holder.itemView.apply {
            var itemName = findViewById<TextView>(R.id.itemName)
            var checkDone = findViewById<CheckBox>(R.id.checkDone)
            itemName.text = currentItem.name
            checkDone.isChecked = currentItem.isChecked
            toggleStrikeThrough(itemName, currentItem.isChecked)
            checkDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(itemName, isChecked)
                currentItem.isChecked = !currentItem.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}