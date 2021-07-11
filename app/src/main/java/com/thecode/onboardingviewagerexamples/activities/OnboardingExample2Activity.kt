package com.thecode.onboardingviewagerexamples.activities


import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.thecode.onboardingviewagerexamples.R
import com.thecode.onboardingviewagerexamples.adapters.OnboardingViewPagerAdapter2
import kotlinx.android.synthetic.main.activity_onboarding_example2.*

class OnboardingExample2Activity : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager2
    private lateinit var btnCreateAccount: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_example2)

        btnCreateAccount = btn_create_account
        btnCreateAccount.setOnClickListener {
            finish()
        }

        mViewPager = findViewById(R.id.viewPager)
        mViewPager.adapter = OnboardingViewPagerAdapter2(this, this)
        TabLayoutMediator(pageIndicator, mViewPager) { _, _ -> }.attach()
        mViewPager.offscreenPageLimit = 1
    }
}
