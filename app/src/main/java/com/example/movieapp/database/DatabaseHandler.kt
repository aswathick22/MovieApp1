package com.example.movieapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_NAME = "UserDatabase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "data"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_PHONE = "phone"
        private const val COLUMN_EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase) {

        val createTableQuery = ("CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_USERNAME TEXT," +
                "$COLUMN_PASSWORD TEXT," +
                "$COLUMN_PHONE TEXT," +
                "$COLUMN_EMAIL TEXT")
        val query = ((((("CREATE TABLE $TABLE_NAME" + " ("
                + COLUMN_ID) + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME) + " TEXT,"
                + COLUMN_PASSWORD) + " TEXT,"
                + COLUMN_PHONE) + " TEXT,"
                + COLUMN_EMAIL) + " TEXT)"
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        val dropTableQuery = ("DROP TABLE IF EXISTS $TABLE_NAME")
        db.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertUser(username: String, password: String, phone: String, email: String) : Long{
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
            put(COLUMN_PHONE, phone)
            put(COLUMN_EMAIL, email)
        }
        val db = writableDatabase
        return db.insert(TABLE_NAME, null, values)
    }

    fun readUser(username: String, password: String) : Boolean{
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(username, password)
        val cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)
        val userExists = cursor.count > 0
        cursor.close()
        return userExists
    }

}