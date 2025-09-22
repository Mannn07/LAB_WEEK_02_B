package com.example.lab_week_02_b

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class ResultActivity : AppCompatActivity() {

    companion object {
        const val COLOR_KEY = "COLOR_KEY"    // digunakan untuk ambil hex color
        const val ERROR_KEY = "ERROR_KEY"    // untuk mengirim status error ke MainActivity (jika perlu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // ambil data warna dari Intent
        val colorCode = intent.getStringExtra(COLOR_KEY) ?: "000000"

        val backgroundScreen = findViewById<ConstraintLayout>(R.id.background_screen)
        val resultMessage    = findViewById<TextView>(R.id.color_code_result_message)

        // coba ubah background jadi warna yang diterima
        try {
            backgroundScreen.setBackgroundColor(Color.parseColor("#$colorCode"))
            resultMessage.text = getString(
                R.string.color_code_result_message,
                colorCode.uppercase()
            )
        } catch (ex: IllegalArgumentException) {
            // kirim balik error ke MainActivity bila warna tidak valid
            val errorIntent = Intent().apply {
                putExtra(ERROR_KEY, true)
            }
            setResult(Activity.RESULT_OK, errorIntent)
            finish()
            return
        }

        // tombol kembali ke MainActivity
        findViewById<Button>(R.id.back_button).setOnClickListener {
            finish()
        }
    }
}
