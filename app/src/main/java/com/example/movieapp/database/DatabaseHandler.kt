package com.example.movieapp.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
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

    fun insertUser(username: String, password: String, phone: String, email: String): Long {
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
            put(COLUMN_PHONE, phone)
            put(COLUMN_EMAIL, email)
        }
        val db = writableDatabase
        return db.insert(TABLE_NAME, null, values)
    }

    fun readUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(username, password)
        val cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)
        val userExists = cursor.count > 0
        cursor.close()
        return userExists
    }

    fun fetchUser(username: String): Map<String, String>? {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_USERNAME = ?"
        Log.d("DatabaseHandler", "Executing query: $query with username: $username")
        val cursor = db.rawQuery(query, arrayOf(username))
        print(cursor)
        return if (cursor.moveToFirst()) {
            val user = mapOf(
            "username" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME)),
            "phone" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE)),
            "email" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)),
            "password" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD))
            )
            cursor.close()
            user
        } else {
            cursor.close()
            null
        }
    }

}