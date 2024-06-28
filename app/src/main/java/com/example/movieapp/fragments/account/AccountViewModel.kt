package com.example.movieapp.fragments.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccountViewModel : ViewModel(){
    private val _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String> get() = _phone

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val validUsername = "aswathi@ck"
    private val validPassword = "ack123"
    private val validPhoneNo = "6282659925"
    private val validEmail = "aswathick@gmail.com"

    private fun updateUsernameValue(){
        _username.value = "aswathi@ck"
    }
    private fun updatePhoneValue(){
        _phone.value = "6282659925"
    }private fun updateEmailValue(){
        _email.value = "aswathick@gmail.com"
    }

    init{
        updateUsernameValue()
        updatePhoneValue()
        updateEmailValue()
    }
}