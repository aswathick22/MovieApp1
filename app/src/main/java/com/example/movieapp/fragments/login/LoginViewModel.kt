package com.example.movieapp.fragments.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.database.DatabaseHandler

class LoginViewModel : ViewModel(){

    private val loginResult: MutableLiveData<Boolean> = MutableLiveData()

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    /*private val validUsername = "aswathi@ck"
    private val validPassword = "ack123"
    private val validPhoneNo = "6282659925"
    private val validEmail = "aswathick@gmail.com"*/

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    fun setUsername(username: String) {
        _username.value = username/*"aswathi@ck"*/
    }

    fun setPassword(password: String) {
        _password.value = password/*"ack123"*/
    }

    fun login(dbHandler : DatabaseHandler) {
        try {
            if (validateInputs()) {
                // Perform login operation
                // If successful, clear the error message
                val isUserExist = dbHandler.readUser(username.value?:"", password.value.orEmpty())
                loginResult.value = isUserExist
                _errorMessage.value = ""
                _loginSuccess.value = true
            }
        } catch (e: Exception) {
            _errorMessage.value = e.message
            _loginSuccess.value = false
        }
    }

    private fun validateInputs(): Boolean {

        val usernameValue = _username.value
        val passwordValue = _password.value

        if (usernameValue.isNullOrBlank()) {
            throw IllegalArgumentException("Username cannot be empty")
        }

        if (passwordValue.isNullOrBlank()) {
            throw IllegalArgumentException("Password cannot be empty")
        }

        // Add logic to check if username or password is incorrect
        if (usernameValue.isEmpty() || passwordValue.isEmpty()) {
            throw IllegalArgumentException("Incorrect username or password")
        }

        return true
    }
    /*private fun updateNameValue(){
        _nameValue2.value = "Login"
    }
    init{
        updateNameValue()
    }*/
}






