package com.data.bmiapp_kris

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.data.bmiapp_kris.databinding.ActivityBmicalculatorBinding

class BMIcalculator : AppCompatActivity() {

    private lateinit var binding: ActivityBmicalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmicalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // intent values
        val height = intent.getDoubleExtra("height", 0.0)
        val weight = intent.getDoubleExtra("weight", 0.0)
        val gender = intent.getStringExtra("gender")
        val fname = intent.getStringExtra("fname")
        val mi = intent.getStringExtra("mi")
        val sname = intent.getStringExtra("sname")
        val genderPrefix = intent.getStringExtra("genderPrefix") ?: ""

        val fullName = "$fname $mi $sname"
        binding.txtFullname.text = " Name: $genderPrefix $fullName"

        val bmi = calculateBMI(height, weight)
        val formattedBMI = String.format("%.2f", bmi)
        binding.txtresult.text = formattedBMI

        val redColor = Color.parseColor("#FF0000")
        val greenColor = Color.parseColor("#00FF00")

        val status = when {
            bmi < 18.5 -> {
                binding.txtstatus.setTextColor(redColor)
                binding.txtsuggest.text = """
                    Note: Being underweight can indicate potential health risks.
                    Consider consulting a healthcare professional to assess your nutritional intake and overall health.
                    They can provide guidance on achieving a healthier weight.
                """.trimIndent()
                "UNDERWEIGHT"
            }

            bmi < 25 -> {
                binding.txtstatus.setTextColor(greenColor)
                binding.txtsuggest.text = """
                    Note: Remember to prioritize balanced nutrition, regular exercise,
                    and routine health check-ups to sustain your healthy lifestyle.
                """.trimIndent()
                "HEALTHY WEIGHT"
            }

            bmi < 30 -> {
                binding.txtstatus.setTextColor(redColor)
                binding.txtsuggest.text = """
                    Note: Consider adopting healthier eating habits and increasing physical activity levels.
                    Small, sustainable changes can lead to significant improvements in your overall health.
                """.trimIndent()
                "OVERWEIGHT"
            }

            else -> {
                binding.txtstatus.setTextColor(redColor)
                binding.txtsuggest.text = """
                    Note: Obesity increases the risk of various health issues,
                    including heart disease, diabetes, and certain cancers.
                    Taking proactive steps now can significantly improve your long-term health.
                """.trimIndent()
                "OBESE"
            }
        }

        binding.txtstatus.text = status

        val range = when {
            bmi < 18.5 -> "Less than 18.5"
            bmi < 25 -> "Greater than or equal 18.5 and less than 25"
            bmi < 30 -> "Greater than or equal to 25 and less than 30"
            else -> "Greater than or equal to 30"
        }

        binding.txtrange.text = range

        val rangeColor = when {
            bmi < 18.5 -> Color.parseColor("#FF0000")
            bmi < 25 -> Color.parseColor("#00FF00")
            else -> Color.parseColor("#FF0000")
        }

        binding.txtrange.setBackgroundColor(rangeColor)
    }

    private fun calculateBMI(height: Double, weight: Double): Double {
        return if (height > 0 && weight > 0) {
            val heightInMeters = height / 100
            weight / (heightInMeters * heightInMeters)
        } else {
            0.0
        }
    }
}