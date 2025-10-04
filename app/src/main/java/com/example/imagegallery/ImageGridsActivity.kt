package com.example.imagegallery

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.*
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
    private lateinit var toggleVolume: ToggleButton
    private lateinit var volumeLayout: LinearLayout
    private lateinit var volumeSeekBar: SeekBar
    private lateinit var volumePercent: TextView
    private var mediaPlayer: MediaPlayer? = null

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

        // Theme switch
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

        // Add images to grid
        gridLayout.removeAllViews()
        gridLayout.addView(imageViewJett)
        gridLayout.addView(imageViewTejo)
        gridLayout.addView(imageViewDeadlock)
        gridLayout.addView(imageViewIso)
        gridLayout.addView(imageViewSova)
        gridLayout.addView(imageViewKilljoy)

        // Radio group filter
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

        // Toggle gallery images
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
        // Iso
        imageViewIso.setOnClickListener { v ->
            val intent = Intent(this, IsoDesc::class.java)
            startActivity(intent)
        }
        // Deadlock
        imageViewDeadlock.setOnClickListener { v ->
            val intent = Intent(this, DeadlockDesc::class.java)
            startActivity(intent)
        }

        // Jett
        imageViewJett.setOnClickListener { v -> val intent =
            Intent(this, JettDesc::class.java)
            startActivity(intent) }

        // Tejo
        imageViewTejo.setOnClickListener { v -> val intent =
            Intent(this, TejoDesc::class.java)
            startActivity(intent) }

        // Killjoy
        imageViewKilljoy.setOnClickListener { v -> val intent =
            Intent(this, KilljoyDesc::class.java)
            startActivity(intent) }
        // Sova
        imageViewSova.setOnClickListener { v -> val intent =
            Intent(this, SovaDesc::class.java)
            startActivity(intent) }
        // --- BGM + SeekBar Implementation (matches the XML you gave) ---
        toggleVolume = findViewById(R.id.toggleVolume)          // Toggle beside gallery toggle
        volumeLayout = findViewById(R.id.volumeLayout)          // whole layout containing seekbar + percent
        volumeSeekBar = findViewById(R.id.volumeSeekBar)        // SeekBar id in XML
        volumePercent = findViewById(R.id.volumePercent)        // TextView id in XML (you used volumePercent)

        // Setup MediaPlayer (put your mp3 in res/raw/background_music.mp3)
        mediaPlayer = MediaPlayer.create(this, R.raw.background_music)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start() // music plays (keeps playing even if seekbar hidden)

        // initial volume 50%
        volumeSeekBar.max = 100
        volumeSeekBar.progress = 50
        mediaPlayer?.setVolume(0.5f, 0.5f)
        volumePercent.text = "50%"


        // SeekBar controls mediaPlayer volume
        volumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val volume = progress / 100f
                mediaPlayer?.setVolume(volume, volume)
                volumePercent.text = "$progress%"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Toggle shows/hides the volumeLayout only — music continues playing
        toggleVolume.setOnCheckedChangeListener { _, isChecked ->
            volumeLayout.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        val buttonBack = findViewById<Button>(R.id.backButton)
        buttonBack.setOnClickListener { v ->
            finish()
        }
    }

    override fun onDestroy() {
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }
}
