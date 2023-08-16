package com.anahitavakoli.apps.simplenotepad.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import com.anahitavakoli.apps.simplenotepad.R
import com.anahitavakoli.apps.simplenotepad.database.NoteDbAdapter
import com.anahitavakoli.apps.simplenotepad.model.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class AddNoteActivity : AppCompatActivity() {

    lateinit var dbAdapter : NoteDbAdapter
    lateinit var calendar : Calendar
    var dateNote = ""
    var timeNote = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        dbAdapter = NoteDbAdapter(applicationContext)

        val btnSave = findViewById<AppCompatButton>(R.id.btn_save)
        val btnTime = findViewById<AppCompatButton>(R.id.btn_time)
        val btnDate = findViewById<AppCompatButton>(R.id.btn_date)
        val edtTitle = findViewById<AppCompatEditText>(R.id.edt_title)
        val edtDesc = findViewById<AppCompatEditText>(R.id.edt_desc)
        val imgBack = findViewById<AppCompatImageView>(R.id.img_back)
        calendar = Calendar.getInstance()

        btnSave.setOnClickListener {
            if (edtDesc.text.toString().isNotEmpty()) {
                var note =
                    Note(0, edtTitle.text.toString(), edtDesc.text.toString(), timeNote, dateNote)
                var result = dbAdapter.insert(note)

                if (result > 0) Snackbar.make(it, "Added!!!", Snackbar.LENGTH_LONG)
                    .show() else Snackbar.make(it, "Nashod!!!", Snackbar.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Please write description", Toast.LENGTH_LONG)
                    .show()
            }

        }

        btnDate.setOnClickListener{
            var dialog = DatePickerDialog(this@AddNoteActivity, object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(p0: DatePicker?, d: Int, m: Int, y: Int) {
                    dateNote = "$d/${m+1}/$y"
                    btnDate.text = dateNote
                }

            },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))

            dialog.show()
        }

        btnTime.setOnClickListener{

            var dialog = TimePickerDialog(this@AddNoteActivity,object:TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(p0: TimePicker?, h: Int, m: Int) {
                    timeNote = "$h:$m"
                    btnTime.text = timeNote
                }

            },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true)

            dialog.show()

        }

        imgBack.setOnClickListener{
            finish()
        }

    }
}