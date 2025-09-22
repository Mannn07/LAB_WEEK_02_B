package com.example.lab_week_02_b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val INPUT_KEY  = "INPUT_KEY"   // hex color tanpa '#', contoh: FF8800
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // pakai layout XML pertama

        val til = findViewById<TextInputLayout>(R.id.color_code_input_wraper)
        val et  = findViewById<TextInputEditText>(R.id.color_code_input_field)
        val submit = findViewById<Button>(R.id.submit_button)

        submit.setOnClickListener {
            val raw = et.text?.toString()?.trim().orEmpty()
            // validasi: 6 digit hex (boleh dengan atau tanpa '#')
            val cleaned = raw.removePrefix("#")
            val isValid = cleaned.matches(Regex("^[0-9A-Fa-f]{6}$"))

            if (!isValid) {
                til.error = "Masukkan 6 digit hex, contoh: FF8800"
                et.requestFocus()
                return@setOnClickListener
            } else {
                til.error = null
            }

            // kirim ke ResultActivity
            val intent = Intent(this, ResultActivity::class.java)
                .putExtra(INPUT_KEY, cleaned.uppercase())
            startActivity(intent)
        }
    }
}
