package com.thecode.onboardingviewagerexamples.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.thecode.onboardingviewagerexamples.R
import com.thecode.onboardingviewagerexamples.activities.OnboardingExample3Activity
import kotlinx.android.synthetic.main.fragment_onboarding1.view.*
import kotlinx.android.synthetic.main.fragment_onboarding1.view.image_onboarding
import kotlinx.android.synthetic.main.fragment_onboarding1.view.text_onboarding_description
import kotlinx.android.synthetic.main.fragment_onboarding1.view.text_onboarding_title
import kotlinx.android.synthetic.main.fragment_onboarding3.view.*
import java.util.*

class OnboardingFragment3 : Fragment() {
    private lateinit var title: String
    private lateinit var description: String
    private lateinit var backgroundColor: String
    private var imageResource = 0
    private lateinit var tvTitle: AppCompatTextView
    private lateinit var tvDescription: AppCompatTextView
    private lateinit var image: ImageView
    private lateinit var layout:  RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            title = arguments!!.getString(ARG_PARAM1)!!
            description = arguments!!.getString(ARG_PARAM2)!!
            imageResource = arguments!!.getInt(ARG_PARAM3)
            backgroundColor = arguments!!.getString(ARG_PARAM4)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootLayout: View =
            inflater.inflate(R.layout.fragment_onboarding3, container, false)
        tvTitle = rootLayout.text_onboarding_title
        tvDescription = rootLayout.text_onboarding_description
        image = rootLayout.image_onboarding
        layout = rootLayout.layout_container
        tvTitle.text = title
        tvDescription.text = description
        image.setImageResource(imageResource)
        layout.setBackgroundColor(Color.parseColor(backgroundColor))
        (activity as OnboardingExample3Activity).updateStatusBarColor(backgroundColor)
        return rootLayout
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        private const val ARG_PARAM3 = "param3"
        private const val ARG_PARAM4 = "param4"
        fun newInstance(
            title: String?,
            description: String?,
            imageResource: Int,
            backgroundColor: String
        ): OnboardingFragment3 {
            val fragment = OnboardingFragment3()
            val args = Bundle()
            args.putString(ARG_PARAM1, title)
            args.putString(ARG_PARAM2, description)
            args.putInt(ARG_PARAM3, imageResource)
            args.putString(ARG_PARAM4, backgroundColor)
            fragment.arguments = args
            return fragment
        }
    }
}
