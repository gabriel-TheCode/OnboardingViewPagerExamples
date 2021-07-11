package com.thecode.onboardingviewagerexamples.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.thecode.onboardingviewagerexamples.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        when (view.id) {

            R.id.btn_example1 -> {
                val intent =
                    Intent(applicationContext, OnboardingExample1Activity::class.java)
                startActivity(intent)
            }

            R.id.btn_example2 -> {
                val intent =
                    Intent(applicationContext, OnboardingExample2Activity::class.java)
                startActivity(intent)
            }
            R.id.btn_example3 -> {
                val intent =
                    Intent(applicationContext, OnboardingExample3Activity::class.java)
                startActivity(intent)
            }
            R.id.btn_example4 -> {
                val intent =
                    Intent(applicationContext, OnboardingExample4Activity::class.java)
                startActivity(intent)
            }
        }
    }

}
