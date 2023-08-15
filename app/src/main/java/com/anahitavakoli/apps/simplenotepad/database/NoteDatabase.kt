package com.anahitavakoli.apps.simplenotepad.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class NoteDatabase(context: Context?) : SQLiteOpenHelper(context, "notes.db", null,  1) {

        val TBL_NAME : String = "tbl_notes"
        val KEY_ID : String = "id"
        val KEY_TITLE : String = "title"
        val KEY_DESCRIPTION : String = "description"
        val KEY_TIME : String = "timeNote"
        val KEY_DATE : String = "dateNote"

    override fun onCreate(db: SQLiteDatabase?) {

        val query = "CREATE TABLE ${TBL_NAME} (${KEY_ID} Integer PRIMARY KEY AUTOINCREMENT," +
                "${KEY_TITLE} varchar(50)," +
                "${KEY_DESCRIPTION} Text," +
                "${KEY_TIME} varchar(50)," +
                "${KEY_DATE} varchar(50));"

        db?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}