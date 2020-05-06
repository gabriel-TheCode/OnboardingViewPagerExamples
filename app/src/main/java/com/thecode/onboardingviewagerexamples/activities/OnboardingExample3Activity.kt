package com.thecode.onboardingviewagerexamples.activities

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.thecode.onboardingviewagerexamples.R
import com.thecode.onboardingviewagerexamples.adapters.OnboardingViewPagerAdapter
import com.thecode.onboardingviewagerexamples.adapters.OnboardingViewPagerAdapter3
import kotlinx.android.synthetic.main.activity_onboarding_example3.*

class OnboardingExample3Activity : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager
    private lateinit var textSkip: TextView
    private lateinit var textEnd: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_example3)
        mViewPager = viewPager
        mViewPager.adapter = OnboardingViewPagerAdapter3(supportFragmentManager, this)
        textSkip = text_skip
        textEnd = text_end

        textSkip.setOnClickListener {
            finish()
        }

        textEnd.setOnClickListener {
            finish()
        }

        val btnNextStep: ImageButton = btn_next_step
        btnNextStep.setOnClickListener {
            if (getItem(+1) > mViewPager.childCount-1) {
              finish()
            } else {
                mViewPager.setCurrentItem(getItem(+1), true)
            }
        }


        if(getItem(+1) == mViewPager.childCount-1){
                btnNextStep.visibility = View.GONE
                textEnd.visibility = View.VISIBLE
            }else{
            btnNextStep.visibility = View.VISIBLE
            textEnd.visibility = View.GONE
        }
    }

    private fun getItem(i: Int): Int {
        return mViewPager.currentItem + i
    }

    fun updateStatusBarColor(color: String) { // Color must be in hexadecimal fromat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.parseColor(color)
        }
    }
}
