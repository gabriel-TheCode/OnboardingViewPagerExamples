package com.thecode.onboardingviewagerexamples.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.thecode.onboardingviewagerexamples.R
import com.thecode.onboardingviewagerexamples.fragments.OnboardingFragment
import com.thecode.onboardingviewagerexamples.fragments.OnboardingFragment3

class OnboardingViewPagerAdapter3(
    fragmentManager: FragmentManager,
    private val context: Context
) :
    FragmentPagerAdapter(fragmentManager) {

    // Returns total number of pages
    override fun getCount(): Int {
        return NUM_ITEMS
    }

    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingFragment3.newInstance(
                context.resources.getString(R.string.title_onboarding_1),
                context.resources.getString(R.string.description_onboarding_1),
                R.drawable.ic_app_user_colour,
                "#4CAF50"
            )
            1 -> OnboardingFragment3.newInstance(
                context.resources.getString(R.string.title_onboarding_2),
                context.resources.getString(R.string.description_onboarding_2),
                R.drawable.ic_gamer_colour,
                "#F44336"
            )
            2 -> OnboardingFragment3.newInstance(
                context.resources.getString(R.string.title_onboarding_3),
                context.resources.getString(R.string.description_onboarding_3),
                R.drawable.ic_social_feed_colour,
                "#2196F3"
            )
            else -> null
        }!!
    }

    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        return "Page $position"
    }

    companion object {
        private const val NUM_ITEMS = 3
    }

}