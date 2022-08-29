package com.thecode.onboardingviewagerexamples.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.thecode.onboardingviewagerexamples.R
import com.thecode.onboardingviewagerexamples.databinding.FragmentOnboarding2Binding

class OnboardingFragment2 : Fragment() {
    private lateinit var title: String
    private lateinit var description: String
    private var imageResource = 0
    private lateinit var tvTitle: AppCompatTextView
    private lateinit var tvDescription: AppCompatTextView
    private lateinit var image: LottieAnimationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            title =
                requireArguments().getString(ARG_PARAM1)!!
            description =
                requireArguments().getString(ARG_PARAM2)!!
            imageResource =
                requireArguments().getInt(ARG_PARAM3)
        }
    }

    private var _binding: FragmentOnboarding2Binding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOnboarding2Binding.inflate(inflater, container, false)
        val view = binding.root
        tvTitle = binding.textOnboardingTitle
        tvDescription = binding.textOnboardingDescription
        image = binding.imageOnboarding
        tvTitle.text = title
        tvDescription.text = description
        image.setAnimation(imageResource)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        private const val ARG_PARAM3 = "param3"
        fun newInstance(
            title: String,
            description: String,
            imageResource: Int
        ): OnboardingFragment2 {
            val fragment =
                OnboardingFragment2()
            val args = Bundle()
            args.putString(
                ARG_PARAM1,
                title
            )
            args.putString(
                ARG_PARAM2,
                description
            )
            args.putInt(
                ARG_PARAM3,
                imageResource
            )
            fragment.arguments = args
            return fragment
        }
    }
}