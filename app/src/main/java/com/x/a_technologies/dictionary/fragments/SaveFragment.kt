package com.x.a_technologies.dictionary.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.x.a_technologies.dictionary.R
import com.x.a_technologies.dictionary.adapter.SaveAdapter
import com.x.a_technologies.dictionary.databinding.FragmentSaveBinding
import com.x.a_technologies.dictionary.room.AppDatabase
import com.x.a_technologies.dictionary.room.MyWordDb

class SaveFragment : Fragment() {

    lateinit var binding: FragmentSaveBinding
    lateinit var appDatabase: AppDatabase
    lateinit var list: ArrayList<MyWordDb>
    lateinit var saveAdapter:SaveAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaveBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        appDatabase = AppDatabase.getInstance(context)
        list = ArrayList()
        val list1 = ArrayList<MyWordDb>()
        list1.addAll(appDatabase.wordDao().getAllMyWordDb())
        list1.forEach {
            if (it.save){
                list.add(it)
            }
        }
        saveAdapter = SaveAdapter(list, object :SaveAdapter.RvClick{
            override fun saveClick(myWordDb: MyWordDb, position: Int) {

                    myWordDb.save = false
                    list.remove(myWordDb)
                    Toast.makeText(context, "${myWordDb.word} olib tashlandi", Toast.LENGTH_SHORT).show()

                appDatabase.wordDao().editWord(myWordDb)
                saveAdapter.notifyItemRemoved(position)
                saveAdapter.notifyItemRangeChanged(position, list.size)
            }
        })
        binding.rvSave.adapter = saveAdapter
        if (list.isEmpty()){
            binding.tvEmpty.visibility = View.VISIBLE
        }else{
            binding.tvEmpty.visibility = View.GONE
        }
    }
}