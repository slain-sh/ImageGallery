package com.example.imagegallery

import android.os.Bundle
import android.widget.ImageView
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ImageGridsActivity : AppCompatActivity() {

    // Declare the views
    private lateinit var toggleGallery: ToggleButton

    private lateinit var imageViewJett: ImageView
    private lateinit var imageViewTejo: ImageView
    private lateinit var imageViewDeadlock: ImageView
    private lateinit var imageViewIso: ImageView
    private lateinit var imageViewSova: ImageView
    private lateinit var imageViewKilljoy: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_image_grids)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Bind views
        toggleGallery = findViewById(R.id.toggleGallery)

        imageViewJett = findViewById(R.id.imageViewJett)
        imageViewTejo = findViewById(R.id.imageViewTejo)
        imageViewDeadlock = findViewById(R.id.imageViewDeadlock)
        imageViewIso = findViewById(R.id.imageViewIso)
        imageViewSova = findViewById(R.id.imageViewSova)
        imageViewKilljoy = findViewById(R.id.imageViewKilljoy)

        // Handle toggle switch
        toggleGallery.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Alternative images
                imageViewJett.setImageResource(R.drawable.jett_alt)
                imageViewTejo.setImageResource(R.drawable.tejo_alt)
                imageViewDeadlock.setImageResource(R.drawable.deadlock_alt)
                imageViewIso.setImageResource(R.drawable.iso_alt)
                imageViewSova.setImageResource(R.drawable.sova_alt)
                imageViewKilljoy.setImageResource(R.drawable.killjoy_alt)
            } else {
                // Original images
                imageViewJett.setImageResource(R.drawable.jett)
                imageViewTejo.setImageResource(R.drawable.tejo)
                imageViewDeadlock.setImageResource(R.drawable.deadlock)
                imageViewIso.setImageResource(R.drawable.iso)
                imageViewSova.setImageResource(R.drawable.sova)
                imageViewKilljoy.setImageResource(R.drawable.killjoy)
            }
        }
    }
}
