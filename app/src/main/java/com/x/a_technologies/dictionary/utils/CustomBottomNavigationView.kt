package com.x.a_technologies.dictionary.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.x.a_technologies.dictionary.R
import com.x.a_technologies.dictionary.databinding.ActivityMainBinding

interface NavigationClickListener{
    fun click(id:Int)
}

class CustomBottomNavigationView(val binding: ActivityMainBinding, val context: Context,
                                 val navigationClickListener: NavigationClickListener) {

    init{
        binding.homeConstraint.setOnClickListener {
            navigationClickListener.click(binding.homeConstraint.id)
            setView(binding.home, binding.homeSelector)
        }

        binding.searchConstraint.setOnClickListener {
            navigationClickListener.click(binding.searchConstraint.id)
            setView(binding.search, binding.searchSelector)
        }

        binding.saveConstraint.setOnClickListener {
            navigationClickListener.click(binding.saveConstraint.id)
            setView(binding.save, binding.saveSelector)
        }
    }

    fun setView(imageView: ImageView, view: View){
        searchAndSetView()
        imageView.setColorFilter(
            ContextCompat.getColor(context, R.color.color_main_backgraund)
            ,android.graphics.PorterDuff.Mode.SRC_IN)
        view.visibility = View.VISIBLE
    }

    fun searchAndSetView(){
        for (i in 0..2){
            getImageView(i).setColorFilter(
                ContextCompat.getColor(context, R.color.gray)
                ,android.graphics.PorterDuff.Mode.SRC_IN)
            getView(i).visibility = View.GONE
        }
    }

    fun getView(position:Int): View {
        when(position){
            0 -> return binding.homeSelector
            1 -> return binding.searchSelector
            2 -> return binding.saveSelector
        }
        return binding.homeSelector
    }

    fun getImageView(position:Int): ImageView {
        when(position){
            0 -> return binding.home
            1 -> return binding.search
            2 -> return binding.save
        }
        return binding.home
    }
}