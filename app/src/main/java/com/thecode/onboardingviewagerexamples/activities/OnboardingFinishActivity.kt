package com.thecode.onboardingviewagerexamples.activities


import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.thecode.onboardingviewagerexamples.R
import kotlinx.android.synthetic.main.activity_onboarding_finish.*

class OnboardingFinishActivity : AppCompatActivity() {
    private lateinit var btnStart: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_finish)
        btnStart = layout_start
        btnStart.setOnClickListener {
            finish()
        }
    }
}
