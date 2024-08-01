package com.example.movieapp.database

import android.content.ContentValues
import android.content.Context
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

    fun getUserId(username: String, password: String): Int? {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT userId FROM users WHERE username = ? AND password = ?",
            arrayOf(username, password)
        )
        return if (cursor.moveToFirst()) {
            cursor.getInt(cursor.getColumnIndexOrThrow("userId"))
        } else {
            null
        }.also {
            cursor.close()
            db.close()
        }
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
        if (oldVersion < newVersion) {
            Log.d("DatabaseHandler", "Upgrading database from version $oldVersion to $newVersion")
            val dropTableQuery = ("ALTER TABLE IF EXISTS $TABLE_NAME")
            db.execSQL(dropTableQuery)
            /*onCreate(db)*/
        }
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
        if (username.isEmpty()) {
            Log.d("DatabaseHandler", "Username is empty")
            return null
        }
        val cursor = db.rawQuery(query, arrayOf(username))
        if (username.isEmpty()) {
            Log.d("DatabaseHandler", "Username is empty")
            return null
        }
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

    fun updateUser(oldUsername: String, newUsername: String, newPhone: String, newEmail: String, newPassword: String): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_USERNAME, newUsername)
            put(COLUMN_PHONE, newPhone)
            put(COLUMN_EMAIL, newEmail)
            put(COLUMN_PASSWORD, newPassword)
        }
        val result = db.update(TABLE_NAME, contentValues, "$COLUMN_USERNAME = ?", arrayOf(oldUsername))
        db.close()
        return result
    }

    /*fun deleteLastFourEntries() {
        val db = this.writableDatabase
        db.execSQL(
            "DELETE FROM $TABLE_NAME WHERE $COLUMN_ID IN (" +
                    "SELECT $COLUMN_ID FROM $TABLE_NAME ORDER BY $COLUMN_ID DESC LIMIT 4)"
        )
        db.close()
    }*/

    /*fun updateUser (currentUsername: String, newUsername: String?, newEmail: String?, newPhone: String?, newPassword: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        newUsername?.let { contentValues.put("username", it) }
        newEmail?.let { contentValues.put("email", it) }
        newPhone?.let { contentValues.put("phone", it) }
        newPassword?.let { contentValues.put("password", it) }

        val success = db.update("data", contentValues, "username=?", arrayOf(currentUsername))
        db.close()
        return success > 0
    }*/

    /*fun deleteUser() {
        val db = this.writableDatabase
        val query = "SELECT MAX($COLUMN_ID) AS max_id FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val lastId = cursor.getInt(cursor.getColumnIndexOrThrow("max_id"))
            db.delete(TABLE_NAME, "$COLUMN_ID = 3", arrayOf(lastId.toString()))
        }
        cursor.close()
        db.close()
    }*/

}