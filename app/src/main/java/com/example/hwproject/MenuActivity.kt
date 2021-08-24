package com.example.hwproject


import android.app.DatePickerDialog
import android.app.SearchManager
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.hwproject.NotificationBuilder
import java.util.*

class MenuActivity : AppCompatActivity() {

    private lateinit var notificationBuilder: NotificationBuilder

    // TODO: 23.08.2021 Запомнить
    val dateListener: DatePickerDialog.OnDateSetListener
    = DatePickerDialog.OnDateSetListener {
        datePicker: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
        println("ACADEMY: year=$year month=$month day=$dayOfMonth")
    }

    val timePickerListener = TimePickerDialog.OnTimeSetListener { view: TimePicker, hourOfDay: Int, minute: Int ->

        println("ACADEMY: hour=$hourOfDay minute=$minute")
    }

    val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        notificationBuilder = NotificationBuilder(this)
        setSupportActionBar(findViewById(R.id.my_toolbar))

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)



        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.menu_settings -> {
                showMessage("Settings")
                true
            }
            R.id.time_picker -> {
                TimePickerDialog(this,
                    timePickerListener,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE), true)
                    .show()
                true
            }
            R.id.menu_profile -> {

                DatePickerDialog(this,
                    dateListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH))
                    .show()
                true
            }
            R.id.menu_file_create -> {
                notificationBuilder.showNotification("Menu", "tapped \"File -> Create\"")
                true
            }

            R.id.menu_file_open -> {
                val dialog = CustomDialog(this)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}