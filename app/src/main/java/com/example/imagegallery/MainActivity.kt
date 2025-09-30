package com.example.imagegallery

import android.os.Bundle
import android.text.InputType
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val confirmPasswordEditText = findViewById<EditText>(R.id.editTextConfirmPassword)
        val showPasswordCheckbox = findViewById<CheckBox>(R.id.checkboxShowPassword)
        val passwordEditTexts: Array<EditText> = arrayOf(passwordEditText, confirmPasswordEditText)

        // Toggle hide/show password feature
        showPasswordCheckbox.setOnClickListener {
            for (editText in passwordEditTexts) {
                if (editText.inputType == InputType.TYPE_CLASS_TEXT
                    or InputType.TYPE_TEXT_VARIATION_PASSWORD)
                {
                    editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                } else {
                    editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                }
                editText.setSelection(editText.text?.length ?: 0)
            }
        }
    }
}