package com.x.a_technologies.dictionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import com.x.a_technologies.dictionary.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

       object : CountDownTimer(3000, 1000){
           override fun onTick(millisUntilFinished: Long) {
               when((millisUntilFinished/1000).toInt()){
                   2 -> binding.circleOne.setImageResource(R.drawable.ic_baseline_circle_24)
                   1 -> binding.circleTwo.setImageResource(R.drawable.ic_baseline_circle_24)
                   0 -> binding.circleThee.setImageResource(R.drawable.ic_baseline_circle_24)
               }
           }

           override fun onFinish() {
               startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
               finish()
           }

       }.start()

    }
}