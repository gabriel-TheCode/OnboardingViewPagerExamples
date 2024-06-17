package com.thecode.onboardingviewagerexamples.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.thecode.onboardingviewagerexamples.R
import com.thecode.onboardingviewagerexamples.adapters.OnboardingViewPagerAdapter4
import com.thecode.onboardingviewagerexamples.databinding.ActivityOnboardingExample4Binding

class OnboardingExample4Activity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingExample4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingExample4Binding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.apply {
            viewPager.adapter = OnboardingViewPagerAdapter4(
                this@OnboardingExample4Activity,
                this@OnboardingExample4Activity
            )
            viewPager.offscreenPageLimit = 1
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    if (position == 2) {
                        btnNextStep.text = getText(R.string.finish)
                    } else {
                        btnNextStep.text = getText(R.string.next)
                    }
                }

                override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
                override fun onPageScrollStateChanged(arg0: Int) {}
            })
            TabLayoutMediator(binding.pageIndicator, viewPager) { _, _ -> }.attach()

            btnNextStep.setOnClickListener {
                if (getItem() > viewPager.childCount) {
                    finish()
                } else {
                    viewPager.setCurrentItem(getItem() + 1, true)
                }
            }

            btnPreviousStep.setOnClickListener {
                if (getItem() == 0) {
                    finish()
                } else {
                    viewPager.setCurrentItem(getItem() - 1, true)
                }
            }
        }

    }

    private fun getItem(): Int {
        return binding.viewPager.currentItem
    }
}
