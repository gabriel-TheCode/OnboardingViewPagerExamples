package com.thecode.onboardingviewagerexamples.activities

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.jaeger.library.StatusBarUtil
import com.thecode.onboardingviewagerexamples.adapters.OnboardingViewPagerAdapter3
import com.thecode.onboardingviewagerexamples.databinding.ActivityOnboardingExample3Binding


class OnboardingExample3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingExample3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingExample3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
            viewPager.adapter = OnboardingViewPagerAdapter3(
                this@OnboardingExample3Activity,
                this@OnboardingExample3Activity
            )
            viewPager.offscreenPageLimit = 1
            TabLayoutMediator(binding.pageIndicator, viewPager) { _, _ -> }.attach()
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    if (position == 2) {
                        btnNextStep.visibility = View.GONE
                        textEnd.visibility = View.VISIBLE
                        textSkip.visibility = View.GONE
                    } else {
                        btnNextStep.visibility = View.VISIBLE
                        textEnd.visibility = View.GONE
                        textSkip.visibility = View.VISIBLE
                    }
                }

                override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
                override fun onPageScrollStateChanged(arg0: Int) {}
            })


            StatusBarUtil.setTranslucentForImageViewInFragment(
                this@OnboardingExample3Activity,
                null
            )

            textSkip.setOnClickListener {
                finish()
            }

            textEnd.setOnClickListener {
                finish()
            }

            val btnNextStep: ImageButton = binding.btnNextStep
            btnNextStep.setOnClickListener {
                if (getItem() > viewPager.childCount) {
                    finish()
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
