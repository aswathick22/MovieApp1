package com.example.movieapp.fragments.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.database.DatabaseHandler

class SignupViewModel : ViewModel(){

    private val signupResult: MutableLiveData<Boolean> = MutableLiveData()

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _phone = MutableLiveData<String>()
    private val phone: LiveData<String> get() = _phone

    private val _email = MutableLiveData<String>()
    private val email: LiveData<String> get() = _email

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _signupSuccess = MutableLiveData<Boolean>()
    val signupSuccess: LiveData<Boolean> get() = _signupSuccess

    fun setUsername(username: String) {
        _username.value = username
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setPhone(phone: String) {
        _phone.value = phone
    }

    fun setEmail(email: String){
        _email.value = email
    }

    fun signup(dbHandler : DatabaseHandler) {
        try {
            if (validateInputs()) {
                // Perform signup operation
                // If successful, clear the error message
                val result = dbHandler.insertUser(_username.value.orEmpty(), _password.value.orEmpty(), _phone.value.orEmpty(), _email.value.orEmpty())
                signupResult.value = result > -1
                _errorMessage.value = ""
                _signupSuccess.value = true
            }
        } catch (e: Exception) {
            _errorMessage.value = e.message
            _signupSuccess.value = false
        }
    }

    private fun validateInputs(): Boolean {
        val usernameValue = _username.value
        val passwordValue = _password.value
        val phoneValue = _phone.value?.toString()
        val emailValue = _email.value

        if (usernameValue.isNullOrBlank() || usernameValue.length < 3 || usernameValue.length > 15) {
            throw IllegalArgumentException("Username must be between 3 and 15 characters")
        }

        if (passwordValue.isNullOrBlank() || passwordValue.length < 8) {
            throw IllegalArgumentException("Password must contain at least 8 characters")
        }

        if (phoneValue.isNullOrBlank() || phoneValue.length != 10) {
            throw IllegalArgumentException("Phone number must be of 10 numbers")
        }

        if (emailValue.isNullOrBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()) {
            throw IllegalArgumentException("Invalid email address")
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



