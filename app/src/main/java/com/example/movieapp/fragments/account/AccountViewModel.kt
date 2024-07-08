package com.example.movieapp.fragments.account

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.database.DatabaseHandler
import com.example.movieapp.database.User

class AccountViewModel : ViewModel(){

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String> get() = _phone

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    private lateinit var dbHandler: DatabaseHandler

    fun fetchUser(context : Context) {
        val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", null)

        if (username != null) {
            val user = dbHandler.getUserByUsername(username)
            if (user != null) {
                _user.value = user
            }
        }
    }

    /*private val validUsername = "aswathi@ck"
    private val validPassword = "ack123"
    private val validPhoneNo = "6282659925"
    private val validEmail = "aswathick@gmail.com"*/

    /*private fun updateUsernameValue(){
        _username.value = "aswathi@ck"*//*username*//*
    }
    private fun updatePhoneValue(){
        _phone.value = "6282659925"*//*phone*//*
    }private fun updateEmailValue(){
        _email.value = "aswathick@gmail.com"*//*email*//*
    }*/

    /*init{
        updateUsernameValue()
        updatePhoneValue()
        updateEmailValue()
    }*/
}