package com.example.movieapp.fragments.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel(){
    private val _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun setUsername(username: String) {
        _username.value = username
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun signup() {
        try {
            if (validateInputs()) {
                // Perform signup operation
                // If successful, clear the error message
                _errorMessage.value = ""
            }
        } catch (e: Exception) {
            _errorMessage.value = e.message
        }
    }

    private fun validateInputs(): Boolean {
        val usernameValue = _username.value
        val passwordValue = _password.value

        if (usernameValue.isNullOrBlank() || usernameValue.length < 3 || usernameValue.length > 15) {
            throw IllegalArgumentException("Username must be between 3 and 15 characters")
        }

        if (passwordValue.isNullOrBlank() || passwordValue.length < 8) {
            throw IllegalArgumentException("Password must contain at least 8 characters")
        }

        return true
    }
    /*private fun updateNameValue(){
        _nameValue1.value = "SignUp"
    }
    init{
        updateNameValue()
    }*/
}

