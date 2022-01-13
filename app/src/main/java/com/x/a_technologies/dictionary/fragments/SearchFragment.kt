package com.x.a_technologies.dictionary.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.x.a_technologies.dictionary.R
import com.x.a_technologies.dictionary.adapter.MyWordDbAdapter
import com.x.a_technologies.dictionary.databinding.FragmentSearchBinding
import com.x.a_technologies.dictionary.room.AppDatabase
import com.x.a_technologies.dictionary.room.MyWordDb
import java.lang.Exception

class SearchFragment : Fragment() {

    lateinit var binding: FragmentSearchBinding
    lateinit var appDatabase: AppDatabase
    lateinit var myWordDbAdapter: MyWordDbAdapter
    lateinit var list: ArrayList<MyWordDb>
    var word: MyWordDb? = null
    private val TAG = "SearchFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)

        binding.audio.setOnClickListener {
            if (word != null) {
                try {
                    val mediaPlayer = MediaPlayer()
                    mediaPlayer.setDataSource(
                        binding.root.context,
                        Uri.parse("https:" + word!!.audioLink)
                    )
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                    Toast.makeText(context, "Audio started", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(context, "Audio error ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.share.setOnClickListener {
            if (word != null) {
                try {
                    val share = Intent()
                    share.action = Intent.ACTION_SEND
                    share.setType("text/plain");
                    share.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    share.putExtra(
                        Intent.EXTRA_TEXT,
                        "${word!!.word} \n" +
                                "${word!!.definition} \n" +
                                "${word!!.example} \n" +
                                "${word!!.audioLink} \n"
                    )
                    startActivity(Intent.createChooser(share, "Share word ..."))
                } catch (e: Exception) {
                    Log.e(TAG, "onCreateView: ${e.message}")
                }
            }
        }
        binding.copy.setOnClickListener {
            if (word != null) {
                val cli = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                cli.setPrimaryClip(ClipData.newPlainText("copy to", word!!.word))
                Toast.makeText(context, "Copy ${word!!.word}", Toast.LENGTH_SHORT).show()
            }
        }

        binding.save.setOnClickListener {
            if (word != null) {
                if (word!!.save){
                    word!!.save = false
                    appDatabase.wordDao().editWord(word!!)
                    binding.imgSave.setImageResource(R.drawable.ic_save_1)
                }else{
                    word!!.save = true
                    appDatabase.wordDao().editWord(word!!)
                    binding.imgSave.setImageResource(R.drawable.ic_save_2)
                }
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        appDatabase = AppDatabase.getInstance(context)
        list = ArrayList()
        list.addAll(appDatabase.wordDao().getAllMyWordDb())

        if (list.isNotEmpty()) {
            word = list.last()
            if (word!!.save) {
                binding.imgSave.setImageResource(R.drawable.ic_save_2)
            } else {
                binding.imgSave.setImageResource(R.drawable.ic_save_1)
            }
        }
        binding.tvSearchWordName.text = word!!.word

        myWordDbAdapter = MyWordDbAdapter(list, object : MyWordDbAdapter.RvClick {
            override fun onClick(myWordDb: MyWordDb, position: Int) {
                word = myWordDb
                binding.tvSearchWordName.text = word!!.word
                if (word!!.save) {
                    binding.imgSave.setImageResource(R.drawable.ic_save_2)
                } else {
                    binding.imgSave.setImageResource(R.drawable.ic_save_1)
                }
            }
        })
        binding.rvSearchHistory.adapter = myWordDbAdapter
    }
}