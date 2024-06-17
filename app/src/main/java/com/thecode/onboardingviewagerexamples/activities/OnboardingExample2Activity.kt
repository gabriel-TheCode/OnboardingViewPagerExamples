package com.thecode.onboardingviewagerexamples.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.thecode.onboardingviewagerexamples.adapters.OnboardingViewPagerAdapter2
import com.thecode.onboardingviewagerexamples.databinding.ActivityOnboardingExample2Binding

class OnboardingExample2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingExample2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingExample2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnCreateAccount.setOnClickListener {
                finish()
            }

            viewPager.adapter = OnboardingViewPagerAdapter2(
                this@OnboardingExample2Activity,
                this@OnboardingExample2Activity
            )
            TabLayoutMediator(binding.pageIndicator, viewPager) { _, _ -> }.attach()
            viewPager.offscreenPageLimit = 1
        }

    }
}
