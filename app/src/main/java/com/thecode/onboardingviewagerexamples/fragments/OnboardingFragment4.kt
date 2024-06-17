package com.thecode.onboardingviewagerexamples.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thecode.onboardingviewagerexamples.databinding.FragmentOnboarding4Binding

class OnboardingFragment4 : Fragment() {
    private lateinit var title: String
    private lateinit var description: String
    private var imageResource = 0
    private var _binding: FragmentOnboarding4Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            requireArguments().apply {
                title = getString(ARG_TITLE)!!
                description = getString(ARG_DESCRIPTION)!!
                imageResource = getInt(ARG_IMAGE_RES)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOnboarding4Binding.inflate(inflater, container, false)
        binding.apply {
            textOnboardingTitle.text = title
            textOnboardingDescription.text = description
            imageOnboarding.setAnimation(imageResource)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_DESCRIPTION = "description"
        private const val ARG_IMAGE_RES = "imageRes"

        fun newInstance(
            title: String,
            description: String,
            imageResource: Int
        ): OnboardingFragment2 {
            val fragment = OnboardingFragment2()
            fragment.arguments = Bundle().apply {
                putString(ARG_TITLE, title)
                putString(ARG_DESCRIPTION, description)
                putInt(ARG_IMAGE_RES, imageResource)
            }

            return fragment
        }
    }
}
