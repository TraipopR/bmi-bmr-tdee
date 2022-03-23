package com.example.bmi_bmr_tdee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bmi_bmr_tdee.databinding.ActivityResultBinding
import kotlin.math.pow

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val height = intent.getDoubleExtra(EXTRA_HEIGHT, 0.0)
        val weight = intent.getDoubleExtra(EXTRA_WEIGHT, 0.0)
        val gender = intent.getIntExtra(EXTRA_GENDER, 0)
        val age = intent.getIntExtra(EXTRA_AGE, 0)
        val exercise = intent.getIntExtra(EXTRA_ACTIVITY, 0)

        val heightM = height / 100
        val bmi = weight / heightM.pow(2)
        binding.txtBMI.text = String.format("%.2f", bmi)

        val bmr = when(gender) {
            0 -> 66 + (13.7 * weight) + (5 * height) - (6.8 * age)
            1 -> 665 + (9.6 * weight) + (1.8 * height) - (4.7 * age)
            else -> 0.0
        }
        binding.txtBMR.text = String.format("%.2f", bmr)

        val tdee = when(exercise) {
            0 -> 1.2 * bmr
            1 -> 1.375 * bmr
            2 -> 1.55 * bmr
            3 -> 1.7 * bmr
            4 -> 1.9 * bmr
            else -> 0.0
        }
        binding.txtDTEE.text = String.format("%.2f", tdee)

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}