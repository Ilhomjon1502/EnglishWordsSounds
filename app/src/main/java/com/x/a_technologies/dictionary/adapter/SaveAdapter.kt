package com.x.a_technologies.dictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.x.a_technologies.dictionary.R
import com.x.a_technologies.dictionary.databinding.ItemRvSaveBinding
import com.x.a_technologies.dictionary.room.MyWordDb

class SaveAdapter(val list: List<MyWordDb>, val rvClick: RvClick) : RecyclerView.Adapter<SaveAdapter.Vh>() {

    inner class Vh(var itemRv: ItemRvSaveBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(myWordDb : MyWordDb, position: Int) {
            itemRv.number.text = (position+1).toString()
            itemRv.itemName.text = myWordDb.word
            itemRv.tvDefinitionItem.text = myWordDb.definition
            itemRv.tvExampleItem.text = myWordDb.example
            if (myWordDb.save){
                itemRv.imgSave.setImageResource(R.drawable.ic_save_2)
            }else{
                itemRv.imgSave.setImageResource(R.drawable.ic_save_1)
            }
            itemRv.root.setOnClickListener{
                rvClick.saveClick(myWordDb, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvSaveBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface RvClick{
        fun saveClick(myWordDb: MyWordDb, position: Int)
    }
}