package com.x.a_technologies.dictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.x.a_technologies.dictionary.databinding.ActivityMainBinding
import com.x.a_technologies.dictionary.fragments.*
import com.x.a_technologies.dictionary.utils.CustomBottomNavigationView
import com.x.a_technologies.dictionary.utils.NavigationClickListener
import com.x.a_technologies.dictionary.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())

        CustomBottomNavigationView(binding, this, object : NavigationClickListener {
            override fun click(id: Int) {
                when(id){
                    R.id.home_constraint -> {
                        replaceFragment(HomeFragment())
                    }
                    R.id.search_constraint -> {
                        replaceFragment(SearchFragment())
                    }
                    R.id.save_constraint -> {
                        replaceFragment(SaveFragment())
                    }
                }
            }
        })

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

    }



    fun replaceFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentConteiner, fragment).commit()
    }

}