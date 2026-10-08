package com.example.myapplication

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etMedicationName: EditText
    private lateinit var btnAddMedication: Button
    private lateinit var llMedicationContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etMedicationName = findViewById(R.id.etMedicationName)
        btnAddMedication = findViewById(R.id.btnAddMedication)
        llMedicationContainer = findViewById(R.id.llMedicationContainer)

        btnAddMedication.setOnClickListener {
            val medName = etMedicationName.text.toString().trim()

            if (medName.isNotEmpty()) {
                addMedicationCard(medName)
                etMedicationName.setText("")
            } else {
                Toast.makeText(this, "Escribe el nombre del medicamento", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addMedicationCard(name: String) {

        val itemLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setBackgroundColor(Color.parseColor("#e3e5e8"))

            setPadding(12.dpToPx(), 12.dpToPx(), 12.dpToPx(), 12.dpToPx())

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 0, 20.dpToPx(), 8.dpToPx())
            layoutParams = params
        }

        val tvName = TextView(this).apply {
            text = name
            setTextColor(Color.BLACK)
            textSize = 13f
            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
        }

        val btnState = Button(this).apply {
            text = "No tomado"
            setTextColor(Color.WHITE)
            textSize = 11f
            backgroundTintList = ColorStateList.valueOf(Color.parseColor("#757575"))

            var isTaken = false

            setOnClickListener {
                isTaken = !isTaken
                if (isTaken) {
                    text = "Tomado"
                    backgroundTintList = ColorStateList.valueOf(Color.parseColor("#4CAF50"))
                } else {
                    text = "No tomado"
                    backgroundTintList = ColorStateList.valueOf(Color.parseColor("#757575"))
                }
            }
        }


        val btnDelete = Button(this).apply {
            text = "X"
            setTextColor(Color.WHITE)
            textSize = 11f
            backgroundTintList = ColorStateList.valueOf(Color.parseColor("#E53935"))

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(6.dpToPx(), 0, 0, 0)
            layoutParams = params

            setOnClickListener {
                llMedicationContainer.removeView(itemLayout)
            }
        }


        itemLayout.addView(tvName)
        itemLayout.addView(btnState)
        itemLayout.addView(btnDelete)


        llMedicationContainer.addView(itemLayout)
    }

    private fun Int.dpToPx(): Int {
        return (this * resources.displayMetrics.density).toInt()
    }
}