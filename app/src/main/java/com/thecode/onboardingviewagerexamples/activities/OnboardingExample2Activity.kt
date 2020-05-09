package com.thecode.onboardingviewagerexamples.activities


import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.thecode.onboardingviewagerexamples.R
import com.thecode.onboardingviewagerexamples.adapters.OnboardingViewPagerAdapter2
import kotlinx.android.synthetic.main.activity_onboarding_example2.*

class OnboardingExample2Activity : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager
    private lateinit var btnCreateAccount: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_example2)

        btnCreateAccount = btn_create_account
        btnCreateAccount.setOnClickListener {
            finish()
        }

        mViewPager = findViewById(R.id.viewPager)
        mViewPager.adapter = OnboardingViewPagerAdapter2(supportFragmentManager, this)
        mViewPager.offscreenPageLimit = 1
    }
}
