package com.anahitavakoli.apps.simplenotepad

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anahitavakoli.apps.simplenotepad.adapter.NoteAdapter
import com.anahitavakoli.apps.simplenotepad.database.NoteDatabase
import com.anahitavakoli.apps.simplenotepad.database.NoteDbAdapter
import com.anahitavakoli.apps.simplenotepad.ui.AddNoteActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var noteDbAdapter: NoteDbAdapter
    lateinit var recyclerNotes : RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = NoteDatabase(applicationContext)
        noteDbAdapter = NoteDbAdapter(applicationContext)

        val btnAdd = findViewById<FloatingActionButton>(R.id.fab_add)
        recyclerNotes = findViewById(R.id.recycler_notes)
        val manager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        recyclerNotes.layoutManager = manager



        btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        var noteList = noteDbAdapter.showNotes()
        var adapter = NoteAdapter(this@MainActivity,noteList)
        recyclerNotes.adapter = adapter
    }
}