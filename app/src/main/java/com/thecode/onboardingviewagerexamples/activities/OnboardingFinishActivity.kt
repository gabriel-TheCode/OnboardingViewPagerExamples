package com.thecode.onboardingviewagerexamples.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thecode.onboardingviewagerexamples.databinding.ActivityOnboardingFinishBinding

class OnboardingFinishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingFinishBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layoutStart.setOnClickListener {
            finish()
        }
    }
}
