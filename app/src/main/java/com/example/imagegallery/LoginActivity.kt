package com.example.imagegallery

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val usernameEditText = findViewById<EditText>(R.id.editTextUsername)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val confirmPasswordEditText = findViewById<EditText>(R.id.editTextConfirmPassword)
        val showPasswordCheckbox = findViewById<CheckBox>(R.id.checkboxShowPassword)
        val passwordEditTexts: Array<EditText> = arrayOf(passwordEditText, confirmPasswordEditText)
        val loginButton = findViewById<Button>(R.id.buttonLogin)
        val loginProgressBar = findViewById<ProgressBar>(R.id.progressBarLogin)

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

        // Handle login press
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            // Basic login validation
            if (username.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                Toast.makeText(this, "Fill up all fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            loginProgressBar.visibility = View.VISIBLE
            loginButton.isEnabled = false

            Handler(Looper.getMainLooper()).postDelayed({
                loginProgressBar.visibility = View.GONE
                loginButton.isEnabled = true

                // load imageGrid intent and start it
                val intent = Intent(this, ImageGridsActivity::class.java)
                intent.putExtra("USERNAME", username)
                startActivity(intent)
            }, 2000) // 2 seconds delay

        }
    }
}