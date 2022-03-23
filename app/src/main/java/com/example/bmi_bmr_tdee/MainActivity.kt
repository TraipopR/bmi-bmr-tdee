package com.example.bmi_bmr_tdee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.bmi_bmr_tdee.databinding.ActivityMainBinding

val EXTRA_HEIGHT = "EXTRA_HEIGHT"
val EXTRA_WEIGHT = "EXTRA_WEIGHT"
val EXTRA_GENDER = "EXTRA_GENDER"
val EXTRA_AGE = "EXTRA_AGE"
val EXTRA_ACTIVITY = "EXTRA_ACTIVITY"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var sex = 0
    private var exercise = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemsSex = resources.getStringArray(R.array.Sex)
        binding.txtSex.adapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, itemsSex)
        binding.txtSex.onItemSelectedListener = object :AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) { sex = position }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        val itemsExercise = resources.getStringArray(R.array.Exercise)
        binding.txtActivity.adapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, itemsExercise)
        binding.txtActivity.onItemSelectedListener = object :AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) { exercise = position }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        binding.btnSubmit.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)

            if (listOf(binding.txtHeight, binding.txtWeight, binding.txtAge).any { it.text.isEmpty() }) {
                Toast.makeText(this@MainActivity, "โปรดกรอกให้ครบทุกช่อง", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            intent.putExtra(EXTRA_HEIGHT, binding.txtHeight.text.toString().toDouble())
            intent.putExtra(EXTRA_WEIGHT, binding.txtWeight.text.toString().toDouble())
            intent.putExtra(EXTRA_GENDER, sex)
            intent.putExtra(EXTRA_AGE, binding.txtAge.text.toString().toInt())
            intent.putExtra(EXTRA_ACTIVITY, exercise)
            startActivity(intent)
        }

    }
}