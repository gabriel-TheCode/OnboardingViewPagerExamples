package com.thecode.onboardingviewagerexamples.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.thecode.onboardingviewagerexamples.R
import com.thecode.onboardingviewagerexamples.fragments.OnboardingFragment3

class OnboardingViewPagerAdapter3(
    fragmentActivity: FragmentActivity,
    private val context: Context
) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingFragment3.newInstance(
                context.resources.getString(R.string.title_onboarding_1),
                context.resources.getString(R.string.description_onboarding_1),
                R.raw.lottie_splash_animation,
                "#4CAF50"
            )
            1 -> OnboardingFragment3.newInstance(
                context.resources.getString(R.string.title_onboarding_2),
                context.resources.getString(R.string.description_onboarding_2),
                R.raw.lottie_girl_with_a_notebook,
                "#F44336"
            )
            else -> OnboardingFragment3.newInstance(
                context.resources.getString(R.string.title_onboarding_3),
                context.resources.getString(R.string.description_onboarding_3),
                R.raw.lottie_messaging,
                "#2196F3"
            )
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}