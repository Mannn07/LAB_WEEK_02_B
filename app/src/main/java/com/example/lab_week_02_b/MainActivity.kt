package com.example.lab_week_02_b

import android.content.Intent // Added import for Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast // Added import for Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText // Make sure this import is present

class MainActivity : AppCompatActivity() {
    companion object {
        private const val COLOR_KEY = "COLOR_KEY"
    }

    // Declare views
    private lateinit var submitButton: Button
    private lateinit var colorCodeInput: TextInputEditText // Declare TextInputEditText

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
        val data = activityResult.data
        val error = data?.getBooleanExtra(ERROR_KEY, false)
        if (error == true) {
            Toast.makeText(this, getString(R.string.color_code_input_invalid), Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        submitButton = findViewById(R.id.submit_button)
        colorCodeInput = findViewById(R.id.color_code_input_field) // Initialize TextInputEditText

        submitButton.setOnClickListener {
            val colorCode = colorCodeInput.text.toString() // Use the initialized view

            if (colorCode.isNotEmpty()) {
                if (colorCode.length < 6) {
                    Toast.makeText(this, getString(R.string.color_code_input_wrong_length), Toast.LENGTH_LONG).show()
                } else {
                    val resultIntent = Intent(this, ResultActivity::class.java)
                    resultIntent.putExtra(COLOR_KEY, colorCode)
                    startActivity(resultIntent)
                }
            } else {
                Toast.makeText(this, getString(R.string.color_code_input_empty), Toast.LENGTH_LONG).show()
            }
        }
    }
}
