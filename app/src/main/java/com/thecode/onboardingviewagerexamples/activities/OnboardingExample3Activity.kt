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
import kotlinx.android.synthetic.main.activity_onboarding_example3.*


class OnboardingExample3Activity : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager2
    private lateinit var textSkip: TextView
    private lateinit var textEnd: TextView
    private lateinit var btnNextStep: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_example3)
        mViewPager = viewPager
        textSkip = text_skip
        textEnd = text_end
        btnNextStep = btn_next_step
        mViewPager.adapter = OnboardingViewPagerAdapter3(this, this)
        mViewPager.offscreenPageLimit = 1
        TabLayoutMediator(pageIndicator, mViewPager) { _, _ -> }.attach()
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


        StatusBarUtil.setTranslucentForImageViewInFragment(this, null)

        textSkip.setOnClickListener {
            finish()
        }

        textEnd.setOnClickListener {
            finish()
        }

        val btnNextStep: ImageButton = btn_next_step
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
