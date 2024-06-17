package com.thecode.onboardingviewagerexamples.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thecode.onboardingviewagerexamples.databinding.FragmentOnboarding3Binding

class OnboardingFragment3 : Fragment() {
    private lateinit var title: String
    private lateinit var description: String
    private lateinit var backgroundColor: String
    private var imageResource = 0
    private var _binding: FragmentOnboarding3Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            requireArguments().apply {
                title = getString(ARG_TITLE)!!
                description = getString(ARG_DESCRIPTION)!!
                imageResource = getInt(ARG_IMAGE_RES)
                backgroundColor = getString(ARG_BACKGROUND_COLOR)!!
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOnboarding3Binding.inflate(inflater, container, false)
        binding.apply {
            textOnboardingTitle.text = title
            textOnboardingDescription.text = description
            imageOnboarding.setAnimation(imageResource)
            layoutContainer.setBackgroundColor(Color.parseColor(backgroundColor))
            fakeStatusbarView.setBackgroundColor(Color.parseColor(backgroundColor))
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_TITLE = "title"
        private const val ARG_DESCRIPTION = "description"
        private const val ARG_IMAGE_RES = "imageRes"
        private const val ARG_BACKGROUND_COLOR = "backgroundColor"
        fun newInstance(
            title: String?,
            description: String?,
            imageResource: Int,
            backgroundColor: String
        ): OnboardingFragment3 {
            val fragment = OnboardingFragment3()
            fragment.arguments = Bundle().apply {
                putString(ARG_TITLE, title)
                putString(ARG_DESCRIPTION, description)
                putInt(ARG_IMAGE_RES, imageResource)
                putString(ARG_BACKGROUND_COLOR, backgroundColor)
            }

            return fragment
        }
    }
}
