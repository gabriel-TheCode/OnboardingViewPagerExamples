package com.thecode.onboardingviewagerexamples.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.thecode.onboardingviewagerexamples.R
import com.thecode.onboardingviewagerexamples.adapters.OnboardingViewPagerAdapter
import com.thecode.onboardingviewagerexamples.utils.Animatoo

class OnboardingExample1Activity : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager
    private lateinit var textSkip: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_example1)

        mViewPager = findViewById(R.id.viewPager)
        mViewPager.adapter = OnboardingViewPagerAdapter(supportFragmentManager, this)

        textSkip = findViewById(R.id.text_skip)
        textSkip.setOnClickListener {
            finish()
            val intent =
                Intent(applicationContext, OnboardingFinishActivity::class.java)
            startActivity(intent)
            Animatoo.animateSlideLeft(this)
        }

        val btnNextStep: Button = findViewById(R.id.btn_next_step)

        btnNextStep.setOnClickListener {
            if (getItem(+1) > 2) {
                finish()
                val intent =
                    Intent(applicationContext, OnboardingFinishActivity::class.java)
                startActivity(intent)
                Animatoo.animateSlideLeft(this)
            } else {
                mViewPager.setCurrentItem(getItem(+1), true)
            }
        }

    }
    private fun getItem(i: Int): Int {
        return mViewPager.currentItem + i
    }

}
