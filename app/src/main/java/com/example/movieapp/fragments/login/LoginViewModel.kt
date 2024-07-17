package com.example.movieapp.fragments.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.database.DatabaseHandler

class LoginViewModel : ViewModel(){

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    fun setUsername(username: String) {
        _username.value = username/*"aswathi@ck"*/
    }

    fun setPassword(password: String) {
        _password.value = password/*"ack123"*/
    }

    fun login(context: Context, dbHandler : DatabaseHandler) {
        try {
            if (LoginValidator.validateLogin(_username, _password)) {
                val isUserExist = dbHandler.readUser(username.value ?: "", password.value.orEmpty())
                if (isUserExist) {
                    // Login successful
                    _loginSuccess.value = true
                    _errorMessage.value = ""
                } else {
                    // Invalid credentials
                    _loginSuccess.value = false
                    _errorMessage.value = ""/*Invalid username or password*/
                }
            }
        } catch (e: Exception) {
            _errorMessage.value = e.message
            _loginSuccess.value = false
        }
    }

    object LoginValidator {
        fun validateLogin(_username: LiveData<String>, _password: LiveData<String>): Boolean {
            val usernameValue = _username.value
            val passwordValue = _password.value

            if (usernameValue.isNullOrBlank()) {
                throw IllegalArgumentException("Username cannot be empty")
            }

            if (passwordValue.isNullOrBlank()) {
                throw IllegalArgumentException("Password cannot be empty")
            }

            if (!passwordValue.matches(".*[A-Z].*".toRegex()) ||
                !passwordValue.matches(".*[a-z].*".toRegex()) ||
                !passwordValue.matches(".*[0-9].*".toRegex()) ||
                !passwordValue.matches(".*[@#\$%^&+=].*".toRegex())
            ) {
                throw IllegalArgumentException("")/*Incorrect Password*/
            }

            return true
        }
    }

    fun saveLoginState(context: Context, isLoggedIn: Boolean) {
        val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", isLoggedIn)
        editor.apply()
    }

    fun saveLoggedInUsername(context: Context, username: String) {
        Log.d("LoginViewModel", "Saving username: $username")
        val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("loggedInUsername", username)
        editor.apply()
    }

}


/*private fun validateInputs(): Boolean {

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
}*/


/*private val loginResult: MutableLiveData<Boolean> = MutableLiveData()*/

/*private fun updateNameValue(){
        _nameValue2.value = "Login"
    }
    init{
        updateNameValue()
    }*/





