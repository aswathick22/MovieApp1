package com.example.movieapp.fragments.account

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.database.DatabaseHandler
import kotlinx.coroutines.launch

class AccountViewModel : ViewModel(){

    private val _username = MutableLiveData<String>()
    val username: MutableLiveData<String> get() = _username

    private val _phone = MutableLiveData<String>()
    val phone: MutableLiveData<String> get() = _phone

    private val _email = MutableLiveData<String>()
    val email: MutableLiveData<String> get() = _email

    private val _password = MutableLiveData<String>()
    val password: MutableLiveData<String> get() = _password

    private lateinit var dbHandler: DatabaseHandler

    fun fetchUser(context : Context, username: String) {
        dbHandler = DatabaseHandler(context)
        val user = dbHandler.fetchUser(username)
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

    fun updateUserDetails(newUsername: String, newPhone: String, newEmail: String, newPassword: String) {
        viewModelScope.launch {
            val oldUsername = username.value ?: return@launch
            val result = dbHandler.updateUser(oldUsername, newUsername, newPhone, newEmail, newPassword)
            if (result > 0) {
                username.postValue(newUsername)
                phone.postValue(newPhone)
                email.postValue(newEmail)
                password.postValue(newPassword)
            } else {
                Log.e("AccountViewModel", "Failed to update user details in database")
            }
        }
    }

    /*fun updateUserDetails(newUsername: String, newPhone: String, newEmail: String, newPassword: String) {
        viewModelScope.launch {
            dbHandler.updateUser(newUsername, newPhone, newEmail, newPassword)
            username.postValue(newUsername)
            phone.postValue(newPhone)
            email.postValue(newEmail)
            password.postValue(newPassword)
        }
    }*/


    /*private val validUsername = "aswathi@ck"
    private val validPassword = "ack123"
    private val validPhoneNo = "6282659925"
    private val validEmail = "aswathick@gmail.com"*/
}