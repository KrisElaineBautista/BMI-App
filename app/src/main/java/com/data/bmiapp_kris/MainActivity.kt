package com.data.bmiapp_kris

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.data.bmiapp_kris.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Btncalculate.setOnClickListener {
            val fname = binding.edtfname.text.toString()
            val mi = binding.edtmi.text.toString()
            val sname = binding.edtsname.text.toString()
            val heightStr = binding.edtHeight.text.toString()
            val weightStr = binding.edtWeight.text.toString()

            if (fname.isNotEmpty() &&
                sname.isNotEmpty() &&
                heightStr.isNotEmpty() &&
                weightStr.isNotEmpty()
            ) {
                val height = heightStr.toDouble()
                val weight = weightStr.toDouble()

                val genderPrefix = when (binding.radioGroupGender.checkedRadioButtonId) {
                    R.id.rbtnMale -> "Mr."
                    R.id.rbtnFemale -> "Ms."
                    else -> ""
                }

                val intent = Intent(this, BMIcalculator::class.java).apply {
                    putExtra("height", height)
                    putExtra("weight", weight)
                    putExtra("mi", mi)
                    putExtra("fname", fname)
                    putExtra("sname", sname)
                    putExtra("genderPrefix", genderPrefix)
                }

                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Please fill up all information!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}