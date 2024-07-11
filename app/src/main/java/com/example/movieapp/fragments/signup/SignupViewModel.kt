package com.example.movieapp.fragments.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.database.DatabaseHandler

class SignupViewModel : ViewModel() {

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

    fun setEmail(email: String) {
        _email.value = email
    }

    fun signup(dbHandler: DatabaseHandler/*<Any?>*/) {
        try {
            if (SignupValidator.validateSignup(_username, _password, _phone, _email)) {
                // Perform signup operation
                // If successful, clear the error message
                val result = dbHandler.insertUser(
                    _username.value.orEmpty(),
                    _password.value.orEmpty(),
                    _phone.value.orEmpty(),
                    _email.value.orEmpty()
                )
                signupResult.value = result > -1
                _errorMessage.value = ""
                _signupSuccess.value = true
            }
        } catch (e: Exception) {
            _errorMessage.value = e.message
            _signupSuccess.value = false
        }
    }

    object SignupValidator {
        fun validateSignup(
            _username: LiveData<String>,
            _password: LiveData<String>,
            _phone: LiveData<String>,
            _email: LiveData<String>
        ): Boolean {
            val usernameValue = _username.value
            val passwordValue = _password.value
            val phoneValue = _phone.value?.toString()
            val emailValue = _email.value

            if (usernameValue.isNullOrBlank() || usernameValue.length < 3 || usernameValue.length > 15)
            {
                throw IllegalArgumentException("Username must be between 3 and 15 characters")
            }

            if (!usernameValue.matches("^[a-zA-Z0-9_@]*$".toRegex()))
            {
                throw IllegalArgumentException("Username can only contain alphanumeric characters and underscores")
            }

            if (passwordValue.isNullOrBlank() || passwordValue.length < 8)
            {
                throw IllegalArgumentException("Password must contain at least 8 characters")
            }

            if (!passwordValue.matches(".*[A-Z].*".toRegex()) || !passwordValue.matches(".*[a-z].*".toRegex()) || !passwordValue.matches(
                    ".*[0-9].*".toRegex()) || !passwordValue.matches(".*[@#\$%^&+=].*".toRegex()))
            {
                throw IllegalArgumentException("Password must contain an uppercase letter, a lowercase letter, a number, and a special character")
            }

            if (phoneValue.isNullOrBlank() || phoneValue.length != 10 || !phoneValue.matches("^[0-9]{10}$".toRegex()))
            {
                throw IllegalArgumentException("Phone number must be of 10 digits")
            }

            if (emailValue.isNullOrBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailValue).matches())
            {
                throw IllegalArgumentException("Invalid email address")
            }

            return true
        }
    }
}


    /*private fun validateInputs(): Boolean {
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
    }*/

    /*private fun updateNameValue(){
        _nameValue1.value = "SignUp"
    }
    init{
        updateNameValue()
    }*/




