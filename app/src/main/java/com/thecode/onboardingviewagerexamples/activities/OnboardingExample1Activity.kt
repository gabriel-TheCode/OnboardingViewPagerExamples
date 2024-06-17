package com.thecode.onboardingviewagerexamples.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.thecode.onboardingviewagerexamples.R
import com.thecode.onboardingviewagerexamples.adapters.OnboardingViewPagerAdapter
import com.thecode.onboardingviewagerexamples.databinding.ActivityOnboardingExample1Binding
import com.thecode.onboardingviewagerexamples.utils.Animatoo

class OnboardingExample1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingExample1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingExample1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            viewPager.adapter = OnboardingViewPagerAdapter(
                this@OnboardingExample1Activity,
                this@OnboardingExample1Activity
            )
            TabLayoutMediator(binding.pageIndicator, viewPager) { _, _ -> }.attach()
            textSkip.setOnClickListener {
                finish()
                val intent =
                    Intent(applicationContext, OnboardingFinishActivity::class.java)
                startActivity(intent)
                Animatoo.animateSlideLeft(this@OnboardingExample1Activity)
            }

            btnNextStep.setOnClickListener {
                if (getItem() > viewPager.childCount) {
                    finish()
                    val intent =
                        Intent(applicationContext, OnboardingFinishActivity::class.java)
                    startActivity(intent)
                    Animatoo.animateSlideLeft(this@OnboardingExample1Activity)
                } else {
                    viewPager.setCurrentItem(getItem() + 1, true)
                }
            }
        }


    }

    private fun getItem(): Int {
        return binding.viewPager.currentItem
    }

}
