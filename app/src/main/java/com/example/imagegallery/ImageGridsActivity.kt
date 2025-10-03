package com.example.imagegallery

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.RadioGroup
import android.widget.GridLayout
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate


class ImageGridsActivity : AppCompatActivity() {
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

        val switchThemeToggle = findViewById<Switch>(R.id.switchThemeToggle)

        switchThemeToggle.isChecked = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
        switchThemeToggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        val agentRoleRadioGroup = findViewById<RadioGroup>(R.id.AgentRolesRadioGroup)
        val gridLayout = findViewById<GridLayout>(R.id.imageGridLayout)

        imageViewJett = findViewById<ImageView>(R.id.imageViewJett)
        imageViewTejo = findViewById<ImageView>(R.id.imageViewTejo)
        imageViewDeadlock = findViewById<ImageView>(R.id.imageViewDeadlock)
        imageViewIso = findViewById<ImageView>(R.id.imageViewIso)
        imageViewSova = findViewById<ImageView>(R.id.imageViewSova)
        imageViewKilljoy = findViewById<ImageView>(R.id.imageViewKilljoy)

        gridLayout.removeAllViews()
        gridLayout.addView(imageViewJett)
        gridLayout.addView(imageViewTejo)
        gridLayout.addView(imageViewDeadlock)
        gridLayout.addView(imageViewIso)
        gridLayout.addView(imageViewSova)
        gridLayout.addView(imageViewKilljoy)

        agentRoleRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioAll -> {
                    gridLayout.removeAllViews()
                    gridLayout.addView(imageViewJett)
                    gridLayout.addView(imageViewTejo)
                    gridLayout.addView(imageViewDeadlock)
                    gridLayout.addView(imageViewIso)
                    gridLayout.addView(imageViewSova)
                    gridLayout.addView(imageViewKilljoy)
                }

                R.id.radioDuelist -> {
                    gridLayout.removeAllViews()
                    gridLayout.addView(imageViewJett)
                    gridLayout.addView(imageViewIso)
                }

                R.id.radioSentinel -> {
                    gridLayout.removeAllViews()
                    gridLayout.addView(imageViewDeadlock)
                    gridLayout.addView(imageViewKilljoy)
                }

                R.id.radioInitiator -> {
                    gridLayout.removeAllViews()
                    gridLayout.addView(imageViewTejo)
                    gridLayout.addView(imageViewSova)
                }
            }
        }

        toggleGallery = findViewById(R.id.toggleGallery)
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

        // agent descriptions
        imageViewIso.setOnClickListener { v ->
            val intent = Intent(this, IsoDesc::class.java)
            startActivity(intent)
        }
        imageViewDeadlock.setOnClickListener { v ->
            val intent = Intent(this, DeadlockDesc::class.java)
            startActivity(intent)
        }
    }
}
