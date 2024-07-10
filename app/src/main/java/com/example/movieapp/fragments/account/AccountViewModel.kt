package com.example.movieapp.fragments.account

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.database.DatabaseHandler

class AccountViewModel : ViewModel(){

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String> get() = _phone

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private lateinit var dbHandler: DatabaseHandler

    fun fetchUser(context : Context, loggedInUsername: String) {
        dbHandler = DatabaseHandler(context)
        val user = dbHandler.fetchUser(loggedInUsername)
        if(user!=null) {
            _username.value = user["username"]
            _phone.value = user["phone"]
            _email.value = user["email"]
            _password.value = user["password"]
            Log.d("AccountViewModel", "Fetched User: $user")
        }
        else{
            Log.d("AccountViewModel", "User not found")
        }
    }


    /*private val validUsername = "aswathi@ck"
    private val validPassword = "ack123"
    private val validPhoneNo = "6282659925"
    private val validEmail = "aswathick@gmail.com"*/
}