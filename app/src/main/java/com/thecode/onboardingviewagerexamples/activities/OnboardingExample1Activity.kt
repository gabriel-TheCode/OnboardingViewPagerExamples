package com.thecode.onboardingviewagerexamples.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.thecode.onboardingviewagerexamples.R
import com.thecode.onboardingviewagerexamples.adapters.OnboardingViewPagerAdapter
import com.thecode.onboardingviewagerexamples.utils.Animatoo
import kotlinx.android.synthetic.main.activity_onboarding_example1.*

class OnboardingExample1Activity : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager2
    private lateinit var textSkip: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_example1)

        mViewPager = viewPager
        mViewPager.adapter = OnboardingViewPagerAdapter(this, this)
        TabLayoutMediator(pageIndicator, mViewPager) { _, _ -> }.attach()
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
            if (getItem() > mViewPager.childCount) {
                finish()
                val intent =
                    Intent(applicationContext, OnboardingFinishActivity::class.java)
                startActivity(intent)
                Animatoo.animateSlideLeft(this)
            } else {
                mViewPager.setCurrentItem(getItem() + 1, true)
            }
        }

    }

    private fun getItem(): Int {
        return mViewPager.currentItem
    }

}
