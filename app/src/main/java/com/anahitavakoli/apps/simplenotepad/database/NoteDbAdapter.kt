package com.anahitavakoli.apps.simplenotepad.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.anahitavakoli.apps.simplenotepad.model.Note

class NoteDbAdapter(context: Context?) : NoteDatabase(context) {

    fun insert(note: Note) : Long{

        val db : SQLiteDatabase = writableDatabase
        val values = ContentValues()
        values.put(KEY_TITLE,note.title)
        values.put(KEY_DESCRIPTION,note.description)
        values.put(KEY_TIME,note.time)
        values.put(KEY_DATE,note.date)
        return db.insert(TBL_NAME,null,values)

    }

    fun edit(note: Note) : Int{

        val db : SQLiteDatabase = writableDatabase
        val values = ContentValues()
        values.put(KEY_TITLE,note.title)
        values.put(KEY_DESCRIPTION,note.description)
        values.put(KEY_TIME,note.time)
        values.put(KEY_DATE,note.date)
        val id = note.id
        var array = listOf<String>(id.toString())
        return db.update(TBL_NAME,values,"id=?",array.toTypedArray())

    }

    @SuppressLint("Range")
    fun showNotes() : MutableList<Note>{

        val db : SQLiteDatabase = readableDatabase
        var cursor = db.rawQuery("select * from $TBL_NAME" , null)

        var noteList = mutableListOf<Note>()

        while(cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
            val title = cursor.getString(cursor.getColumnIndex(KEY_TITLE))
            val description = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION))
            val time = cursor.getString(cursor.getColumnIndex(KEY_TIME))
            val date = cursor.getString(cursor.getColumnIndex(KEY_DATE))
            val note  = Note(id,title,description,time,date)
            noteList.add(note)

        }

        return noteList

    }

    @SuppressLint("Range")
    fun search(text : String) : List<Note> {

        var db : SQLiteDatabase = readableDatabase
        val cursor = db.rawQuery("select * from $TBL_NAME WHERE $KEY_TIME like %'$text'% " , null)
        var noteList = mutableListOf<Note>()

        while(cursor.moveToNext()){

            val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
            val title = cursor.getString(cursor.getColumnIndex(KEY_TITLE))
            val description = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION))
            val time = cursor.getString(cursor.getColumnIndex(KEY_TIME))
            val date = cursor.getString(cursor.getColumnIndex(KEY_DATE))
            val note  = Note(id,title,description,time,date)
            noteList.add(note)

        }

        return noteList
    }

    fun deleteAll() : Int {
        val db : SQLiteDatabase = writableDatabase
        return db.delete(TBL_NAME,null,null)
    }

    fun deleteNote(noteId: Int) : Int{
        val db : SQLiteDatabase = writableDatabase
        var array = listOf<String>(noteId.toString())
        return db.delete(TBL_NAME,"id=?",array.toTypedArray())
    }

}