package com.thecode.onboardingviewagerexamples.activities

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.jaeger.library.StatusBarUtil
import com.thecode.onboardingviewagerexamples.R
import com.thecode.onboardingviewagerexamples.adapters.OnboardingViewPagerAdapter3
import com.thecode.onboardingviewagerexamples.databinding.ActivityOnboardingExample3Binding


class OnboardingExample3Activity : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager2
    private lateinit var textSkip: TextView
    private lateinit var textEnd: TextView
    private lateinit var btnNextStep: ImageButton

    private lateinit var binding: ActivityOnboardingExample3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingExample3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mViewPager = binding.viewPager
        textSkip = binding.textSkip
        textEnd = binding.textEnd
        btnNextStep = binding.btnNextStep
        mViewPager.adapter = OnboardingViewPagerAdapter3(this, this)
        mViewPager.offscreenPageLimit = 1
        TabLayoutMediator(binding.pageIndicator, mViewPager) { _, _ -> }.attach()
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
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


        StatusBarUtil.setTranslucentForImageViewInFragment(this, null)

        textSkip.setOnClickListener {
            finish()
        }

        textEnd.setOnClickListener {
            finish()
        }

        val btnNextStep: ImageButton = binding.btnNextStep
        btnNextStep.setOnClickListener {
            if (getItem() > mViewPager.childCount) {
                finish()
            } else {
                mViewPager.setCurrentItem(getItem() + 1, true)
            }
        }
    }

    private fun getItem(): Int {
        return mViewPager.currentItem
    }
}
