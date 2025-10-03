package com.example.imagegallery

import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.*

class KilljoyDesc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_killjoy_desc)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Fixed Calendar no Actions/Events
        val calendarView = findViewById<CalendarView>(R.id.calendarView)

        val myDate = Calendar.getInstance()
        myDate.set(2020, Calendar.AUGUST, 4) // Year, Month (0-based), Day
        val dateInMillis = myDate.timeInMillis

        calendarView.minDate = dateInMillis
        calendarView.maxDate = dateInMillis

        calendarView.date = dateInMillis

        calendarView.isEnabled = false
        calendarView.setOnDateChangeListener { _, _, _, _ ->
        }

        // Back Button
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
    }
}
