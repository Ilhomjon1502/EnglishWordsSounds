package com.x.a_technologies.dictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.x.a_technologies.dictionary.databinding.TabItemLayoutBinding
import com.x.a_technologies.dictionary.room.MyWordDb

class MyWordDbAdapter(val list: List<MyWordDb>, val rvClick: RvClick) : RecyclerView.Adapter<MyWordDbAdapter.Vh>() {

    inner class Vh(var itemRv: TabItemLayoutBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(myWordDb: MyWordDb, position: Int) {
            itemRv.number.text = (position+1).toString()
            itemRv.itemName.text = myWordDb.word
            itemRv.tvDefinitionItem.text = myWordDb.definition
            itemRv.tvExampleItem.text = myWordDb.example
            itemRv.root.setOnClickListener{
                rvClick.onClick(myWordDb, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(TabItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface RvClick{
        fun onClick(myWordDb: MyWordDb, position: Int)
    }
}